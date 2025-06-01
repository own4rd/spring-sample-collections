# 🔐 Spring Roles Hierarchy - Spring Boot + Spring Security

Projeto demonstrando como configurar **hierarquia de papéis (roles)** no Spring Security.

Com a hierarquia, você define que, por exemplo, `ADMIN` herda permissões de `MODERATOR`, que por sua vez herda de `USER`. Isso evita ter que atribuir manualmente todas as roles a um administrador.

---

## 🧱 Tecnologias

- Java 17
- Spring Boot 3.5.0
- Spring Security
- Spring Web
- Maven

---

## ⚙️ Configuração de Hierarquia

A hierarquia é definida no `SecurityConfig.java`:

```java
@Bean
public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_ADMIN > ROLE_MODERATOR \n ROLE_MODERATOR > ROLE_USER";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
}
```

---

## 🔐 Usuários de exemplo

O projeto usa `InMemoryUserDetailsManager` com 3 usuários:

| Usuário     | Senha | Role           |
|-------------|-------|----------------|
| `admin`     | 123   | ROLE_ADMIN     |
| `moderator` | 123   | ROLE_MODERATOR |
| `user`      | 123   | ROLE_USER      |

---

## 🔍 Endpoints disponíveis

| Método | Endpoint         | Papel necessário |
|--------|------------------|------------------|
| GET    | `/api/user`      | `ROLE_USER`      |
| GET    | `/api/moderator` | `ROLE_MODERATOR` |
| GET    | `/api/admin`     | `ROLE_ADMIN`     |

---

## 🚀 Executando o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/spring-roles-hierarchy.git
   ```

2. Acesse a pasta e execute:
   ```bash
   cd spring-roles-hierarchy
   ./mvnw spring-boot:run
   ```

   ou

   ```bash
   mvn spring-boot:run
   ```

---

## 🧪 Exemplos de requisição (cURL)

### ✅ Acesso permitido

```bash
curl -u admin:123 http://localhost:8080/api/user
# Resultado: Acesso: USER

curl -u admin:123 http://localhost:8080/api/admin
# Resultado: Acesso: ADMIN
```

### ❌ Acesso negado

```bash
curl -u user:123 http://localhost:8080/api/admin
# Resultado: 403 Forbidden
```

---
