# RedOrDead — API REST Roulette (R3-03)

API REST **Spring Boot 3 / PostgreSQL** conteneurisée avec Docker, sécurisée par **JWT** et documentée via **Swagger UI**.

---

## 1. Lancement de l'API

À la racine du projet, lancer les conteneurs :

```bash
docker compose up -d --build
```

> Attendre ~15 secondes le démarrage complet de Spring Boot.

| Service | Lien / Adresse | Identifiants |
| :--- | :--- | :--- |
| **API Backend** | `http://localhost:8084` | — |
| **Swagger UI** | `http://localhost:8084/swagger-ui/index.html` | Documentation interactive |
| **Adminer (BDD)** | `http://localhost:8081` | Serveur: `postgres` · User: `postgres` · Pass: `postgrespassword` · BDD: `base_db` |

---

## 2. Fonctionnement de l'Authentification

L'API utilise une authentification **Stateless via JWT (JSON Web Token)** :

1. **Inscription (`/api/auth/register`) :** Le mot de passe est haché avec `BCrypt` avant stockage en base (`joueur`).
2. **Connexion (`/api/auth/login`) :** Le serveur vérifie le mot de passe et génère un token JWT signé.
3. **Protection :** Le filtre `JwtFilter` intercepte chaque requête protégée (`/api/joueurs/**`, `/api/parties/**`), valide l'en-tête `Authorization: Bearer <token>` et injecte l'utilisateur dans le contexte Spring Security.

---

## 3. Procédure de Connexion & Test

### Option A : Via Bruno *(collection incluse dans `/bruno`)*

1. **Créer son compte (`POST` Register)**
   - **URL :** `http://localhost:8084/api/auth/register`
   - **Onglet `Body` → `JSON` :**
     ```json
     {
       "pseudo": "gaby",
       "password": "123"
     }
     ```
   - Cliquer sur **Send** → Statut `200 OK`.

2. **Se connecter (`POST` Login)**
   - **URL :** `http://localhost:8084/api/auth/login`
   - **Onglet `Body` → `JSON` :**
     ```json
     {
       "pseudo": "gaby",
       "password": "123"
     }
     ```
   - Cliquer sur **Send** → Copier la valeur de `"token"` dans la réponse.

3. **Accéder aux routes protégées (ex : `GET` Parties)**
   - **URL :** `http://localhost:8084/api/parties`
   - **Onglet `Auth` → `Bearer Token` :** Coller le token et cliquer sur **Send**.

---

### Option B : Via Swagger UI (`http://localhost:8084/swagger-ui/index.html`)

1. **S'inscrire :** Exécuter `POST /api/auth/register` avec `{"pseudo": "gaby", "password": "123"}`.
2. **Se connecter :** Exécuter `POST /api/auth/login` avec les mêmes identifiants → copier le token reçu.
3. **S'authentifier :** Cliquer sur **Authorize** (en haut à droite), coller le token et valider.
4. **Tester :** Appeler les routes `/api/parties`, `/api/joueurs` ou `/api/externe/conseil`.

---

### Option C : Via Terminal (`curl`)

```bash
# 1. Inscription
curl -X POST http://localhost:8084/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"pseudo": "gaby", "password": "123"}'

# 2. Connexion
curl -X POST http://localhost:8084/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"pseudo": "gaby", "password": "123"}'

# 3. Requête authentifiée
curl http://localhost:8084/api/parties \
  -H "Authorization: Bearer <TON_TOKEN_JWT>"
```
