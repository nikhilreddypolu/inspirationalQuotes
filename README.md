---

## 🚀 Features
- `GET /api/quote` → Returns a random inspirational quote.
- Rate limiting: **5 requests per IP per minute**.
- Logs each request with IP and response status.
- Returns `429 Too Many Requests` if limit exceeded.
- Swagger/OpenAPI documentation included.

------------------------------------------------

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository
```sh
git clone https://github.com/nikhilreddypolu
/inspirationalQuotes.git
cd inspirationalQuotes

2️⃣ Build & Run
mvn clean install
mvn spring-boot:run

3️⃣ Test API
curl http://localhost:8080/api/quote

Example Response
{
  "quote": "The only way to do great work is to love what you do. - Steve Jobs"
}
If rate limit exceeded:
{
  "error": "Rate limit exceeded. Try again in 30 seconds."
}
📖 Swagger Docs
Once running, visit:
http://localhost:8080/swagger-ui.html
-------------------------------------------------------------------------
🛠️ Tech Stack
Java 17
Spring Boot 3.x
Spring Web
Lombok
Springdoc OpenAPI (Swagger)
