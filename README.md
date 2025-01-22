# <h1 align="center">LiterAlura - Consumindo APIs e Persistindo Dados com Java</h1>

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
<img src="https://img.shields.io/badge/Gutendex_API-000?style=for-the-badge&logo=api&logoColor=red"/>
</p>

---

## <h2 align="center">Descrição do Projeto</h2>

O **LiterAlura** é um projeto desenvolvido para consumir dados da API gratuita **Gutendex** e persistir as informações obtidas em um banco de dados relacional. O projeto simula um catálogo interativo de livros, onde o usuário pode buscar livros na API, armazená-los no banco de dados e realizar diversas consultas baseadas em diferentes critérios.

---

## <h2 align="center">Índice</h2>

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Objetivos do Projeto](#objetivos-do-projeto)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Pré-requisitos](#pré-requisitos)
* [Como Executar o Projeto](#como-executar-o-projeto)
* [Autor](#autor)

---

## <h2 align="center">Funcionalidades</h2>

🔨 Funcionalidades do projeto:

- Consulta de informações sobre livros a partir da API [Gutendex](https://gutendex.com/).
- Conversão de dados JSON para objetos Java utilizando **Jackson**.
- Persistência dos dados no banco de dados **PostgreSQL**.
- Menu interativo no console com opções como:
  - Buscar livros por título.
  - Listar todos os livros salvos.
  - Remover livros pelo ID.
  - Consultar autores associados a um livro.
  - Listar autores vivos em determinado ano.
  - Contar livros por idioma.

---

## <h2 align="center">Objetivos do Projeto</h2>

📚 Este projeto foi desenvolvido para:
- Aprender a consumir APIs HTTP em Java com **RestTemplate**.
- Manipular dados JSON com a biblioteca **Jackson**.
- Persistir informações em um banco de dados relacional usando **Spring Data JPA**.
- Criar um menu interativo para manipular os dados de maneira prática.
- Consolidar os conhecimentos de **Java**, **Spring Boot** e **PostgreSQL**.

---

## <h2 align="center">Tecnologias Utilizadas</h2>

- **Linguagem de Programação:**  
![Java](https://img.shields.io/badge/java-000.svg?style=for-the-badge&logo=openjdk&logoColor=%23ED8B00)

- **Frameworks e Bibliotecas:**  
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)  
![Jackson](https://img.shields.io/badge/Jackson-000?style=for-the-badge&logo=json&logoColor=white)

- **Banco de Dados:**  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

- **Ferramentas:**  
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000?style=for-the-badge&logo=intellijidea)  
![Gutendex API](https://img.shields.io/badge/Gutendex_API-000?style=for-the-badge&logo=api&logoColor=red)

---

## <h2 align="center">Pré-requisitos</h2>

Antes de começar, certifique-se de ter os seguintes itens instalados no seu ambiente:

- **Java JDK** 17 ou superior.  
- **PostgreSQL** configurado com uma base de dados para o projeto.  
- **Spring Boot** (recomendado: versão 3.2.3).

---

## <h2 align="center">Como Executar o Projeto</h2>

### 📁 Acesso ao projeto

Clone o repositório com o comando:

```bash
git clone https://github.com/seuusuario/literalura.git
```

### 🛠️ Configurar e executar o projeto

1. Abra o projeto no IntelliJ IDEA ou outra IDE de sua preferência.
2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
3. Compile e execute o projeto.
4. Siga as instruções exibidas no console para interagir com o catálogo de livros.

---

## <h2 align="center">Autor</h2>

<div align="center">

| [<img src="https://avatars.githubusercontent.com/u/172914574?v=4" width=115><br><sub>Natalia Oliveira</sub>](https://github.com/nataliaoliveiradev) |
| :---: |
| **Bacharel em Direito, estudante de Ciência da Computação e entusiasta do desenvolvimento fullstack.** |

</div>

---

## <h2 align="center">Contato</h2>

[![LinkedIn](https://img.shields.io/badge/LinkedIn-000?style=for-the-badge&logo=linkedin&logoColor=ff6e96)](https://www.linkedin.com/in/nataliaoliveiradev/)  
[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github&logoColor=ff6e96)](https://github.com/nataliaoliveiradev)  
[![E-mail](https://img.shields.io/badge/-Email-000?style=for-the-badge&logo=microsoft-outlook&logoColor=ff6e96)](mailto:profissionalnataliaoliveira@gmail.com)

---

## <h2 align="center">Licença</h2>

<p align="center">
Este projeto está licenciado sob a licença MIT. Para mais detalhes, consulte o arquivo LICENSE.
</p>

---
```
