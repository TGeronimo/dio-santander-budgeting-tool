# Budgeting - Registro de Transações por Áudio

Aplicação Spring Boot para registrar despesas a partir de entrada de áudio usando IA para transcrição e síntese de voz. Suporta registro de usuários e autenticação JWT. Ao enviar um áudio, a aplicação transcreve, processa a intenção (cria a transação) e retorna um áudio confirmando o registro.

Principais funcionalidades
- Registro e login de usuários (JWT)
- Persistência de transações (categorias, valor em centavos)
- Transcrição de áudio (upload multipart)
- Geração de áudio (Text-to-Speech) de confirmação
- Integração com Spring AI (OpenAI)

Tecnologias
- Java 21+ / Spring Boot
- Spring Security + JWT
- Spring Data JPA (configurar datasource: MySQL/Postgres/H2)
- Spring AI (transcription, chat, text-to-speech)
- Gradle (wrapper incluído)
- Docker Compose (arquivo compose.yml provê MySQL de exemplo)

Pré-requisitos
- JDK 17+
- Docker (opcional, para banco via compose)
- Variáveis de ambiente mínimas:
  - OPENAI_API_KEY - chave para OpenAI (transcribe/tts/chat)
  - JWT_SECRET - se quiser sobrescrever jwt.secret (default de teste presente em application.properties)
  - (opcional) spring.datasource.url, spring.datasource.username, spring.datasource.password

Executando localmente (Windows)
1. Opcional: subir banco MySQL via Docker Compose (compose.yml):
   docker compose -f compose.yml up -d

2. Build / run com gradle wrapper:
   gradlew.bat clean build
   gradlew.bat bootRun

A aplicação roda por padrão em http://localhost:8080

Endpoints principais

1) Autenticação (abertos)
- POST /auth/register
  - Body JSON: { "email": "user@example.com", "password": "senha" }
  - Retorna: id, email, password (hash armazenado)

- POST /auth/login
  - Body JSON: { "email":"user@example.com", "password":"senha" }
  - Retorna: { "accessToken": "...", "refreshToken": "..." }

- POST /auth/refresh
  - Body JSON: { "refreshToken": "..." }
  - Retorna novo access + refresh tokens

2) Transações (requer autorização)
- POST /transactions
  - Body JSON: { "description":"Compra no mercado", "category":"GROCERIES", "amount": 450 } 
  - amount: valor em centavos (ex.: 4 reais = 400)
  - Retorno: TransactionResponse (id, description, category, amount, createdAt)

- GET /transactions/{category}
  - category: GROCERIES | PHARMA | AUTO
  - Retorno: lista de transações daquela categoria

3) Fluxo IA / Áudio (requer autorização)
- POST /transactions/ai
  - multipart/form-data com campo file (arquivo de áudio)
  - Ação: transcreve áudio, processa com ChatClient e TextToSpeech para gerar confirmação
  - Retorna: attachment audio/mp3 (arquivo audio.mp3)

- POST /api/transcribe
  - multipart/form-data field file
  - Retorna: texto transcrito (plain text)

- POST /api/synthesize
  - Body JSON: { "text": "texto a sintetizar" }
  - Retorna: attachment audio/mp3

Proteção e cabeçalhos
- Endpoints em /api/** (transcribe/synthesize/chat) exigem Authorization: Bearer <accessToken>
- /auth/** liberados conforme SecurityConfig

Exemplos curl
- Registro:
  curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d "{\"email\":\"user@example.com\",\"password\":\"senha\"}"

- Login:
  curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d "{\"email\":\"user@example.com\",\"password\":\"senha\"}"

- Criar transação (usar token se sua configuração exigir):
  curl -X POST http://localhost:8080/transactions -H "Content-Type: application/json" -H "Authorization: Bearer <TOKEN>" -d "{\"description\":\"Compra mercado\",\"category\":\"GROCERIES\",\"amount\":1200}"

- Upload de áudio para transcrição (Bearer token necessário para /api/transcribe):
  curl -X POST http://localhost:8080/api/transcribe -H "Authorization: Bearer <TOKEN>" -F "file=@caminho/arquivo.mp3"

- Upload de áudio para criar transação + receber áudio de confirmação:
  curl -X POST http://localhost:8080/transactions/ai -F "file=@caminho/arquivo.mp3" --output confirmacao.mp3

Observações e dicas
- Amount é em centavos. Padronize clientes para enviar valores inteiros (long).
- Ajustar application.properties para linguagem/vozes/modelos do Spring AI conforme necessidade.
- Em ambientes de produção, não deixar jwt.secret com valor hard-coded em properties; use variáveis de ambiente/secret manager.

Contribuição
- Fork + PR. Siga convenções do projeto (Gradle) e execute build localmente.

Licença
- MIT (ou ajuste conforme desejado)
