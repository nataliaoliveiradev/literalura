package com.alura.literalura.console;

import com.alura.literalura.dto.AutorDTO;
import com.alura.literalura.dto.LivroDTO;
import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.titulo.Autor;
import com.alura.literalura.service.ServicoAutor;
import com.alura.literalura.service.ServicoLivro;
import com.alura.literalura.service.GutendexService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class AplicacaoConsole implements CommandLineRunner {

    private final GutendexService gutendexService;
    private final ServicoLivro servicoLivro;
    private final ServicoAutor servicoAutor;

    public AplicacaoConsole(GutendexService gutendexService, ServicoLivro servicoLivro, ServicoAutor servicoAutor) {
        this.gutendexService = gutendexService;
        this.servicoLivro = servicoLivro;
        this.servicoAutor = servicoAutor;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1 -> searchBooks(scanner);
                case 2 -> showBooks();
                case 3 -> deleteBook(scanner);
                case 4 -> showAuthorsByBook(scanner);
                case 5 -> showAllAuthors();
                case 6 -> exit();
                case 7 -> showBooksByLanguage(scanner);
                case 8 -> showAuthorsAliveInYear(scanner);
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void showMenu() {
        System.out.println("Catálogo de Livros - Opções:");
        System.out.println("1. Buscar livros");
        System.out.println("2. Mostrar livros salvos");
        System.out.println("3. Remover livro por ID");
        System.out.println("4. Mostrar autores por livro");
        System.out.println("5. Mostrar todos os autores");
        System.out.println("6. Sair");
        System.out.println("7. Mostrar quantidade de livros por idioma");
        System.out.println("8. Listar autores vivos em um ano específico");
        System.out.print("Selecione uma opção: ");
    }

    private int getUserChoice(Scanner scanner) {
        int choice = -1;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private void searchBooks(Scanner scanner) {
        System.out.print("Digite o termo de busca: ");
        String query = scanner.nextLine();
        List<GutendexResponse.Book> books = gutendexService.searchBooksByTitle(query);
        displayBooks(books);

        if (books != null && !books.isEmpty()) {
            System.out.print("Deseja salvar algum desses livros? (s/n): ");
            String saveChoice = scanner.nextLine();
            if (saveChoice.equalsIgnoreCase("s")) {
                System.out.print("Digite o número do livro que deseja salvar: ");
                try {
                    int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (bookIndex >= 0 && bookIndex < books.size()) {
                        gutendexService.storeBook(books.get(bookIndex));
                    } else {
                        System.out.println("Seleção inválida. Certifique-se de inserir um número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número válido.");
                }
            }
        }
    }

    @Transactional
    public void showBooks() {
        List<LivroDTO> books = servicoLivro.getAllBooksDTO();
        if (books.isEmpty()) {
            System.out.println("Nenhum livro salvo.");
        } else {
            books.forEach(book -> System.out.println(book));
        }
    }

    private void deleteBook(Scanner scanner) {
        System.out.print("Digite o ID do livro a ser removido: ");
        Long id = null;
        boolean validId = false;
        while (!validId) {
            try {
                id = scanner.nextLong();
                scanner.nextLine();
                validId = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido para o ID.");
                scanner.nextLine();
            }
        }
        servicoLivro.deleteBookById(id);
        System.out.println("Livro removido.");
    }

    @Transactional
    public void showAuthorsByBook(Scanner scanner) {
        System.out.print("Digite o ID do livro: ");
        Long bookId = getValidLong(scanner);
        List<AutorDTO> authors = servicoAutor.getAuthorsByBookId(bookId);
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o livro com ID " + bookId);
        } else {
            System.out.println("Autores para o livro com ID " + bookId + ":");
            authors.forEach(author -> System.out.println(author));
        }
    }

    @Transactional
    public void showAllAuthors() {
        List<AutorDTO> authors = servicoAutor.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            authors.forEach(author -> System.out.println(author));
        }
    }

    private void exit() {
        System.out.println("Saindo...");
        System.exit(0);
    }

    private void displayBooks(List<GutendexResponse.Book> books) {
        if (books == null || books.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o termo de busca.");
            return;
        }

        System.out.println("Livros encontrados:");
        for (int i = 0; i < books.size(); i++) {
            GutendexResponse.Book book = books.get(i);

            String authors = (book.getAuthors() != null && !book.getAuthors().isEmpty())
                    ? book.getAuthors().stream()
                    .map(author -> author.getName() + " (" + author.getBirth_year() + " - " + author.getDeath_year() + ")")
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Sem autor")
                    : "Sem autor";

            System.out.printf("%d. %s por %s%n", i + 1, book.getTitle(), authors);

            for (GutendexResponse.Author author : book.getAuthors()) {
                Autor entityAutor = new Autor(
                        author.getName(),
                        author.getBirth_year(),
                        author.getDeath_year(),
                        null
                );

                AutorDTO autorDTO = new AutorDTO(entityAutor);
                System.out.println(autorDTO);
            }
        }
    }

    private Long getValidLong(Scanner scanner) {
        Long value = null;
        boolean valid = false;
        while (!valid) {
            try {
                value = scanner.nextLong();
                scanner.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
        return value;
    }

    private void showBooksByLanguage(Scanner scanner) {
        System.out.print("Digite os idiomas separados por vírgula (por exemplo, 'en,es,fr'): ");
        String input = scanner.nextLine();
        String[] languages = input.split(",");

        if (languages.length < 2) {
            System.out.println("É necessário informar pelo menos dois idiomas.");
            return;
        }

        for (String language : languages) {
            String trimmedLanguage = language.trim();
            long count = servicoLivro.countBooksByLanguage(trimmedLanguage);
            System.out.printf("Quantidade de livros em '%s': %d%n", trimmedLanguage, count);
        }
    }

    private void showAuthorsAliveInYear(Scanner scanner) {
        System.out.print("Digite o ano para buscar autores vivos: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um ano válido.");
            return;
        }

        List<AutorDTO> authors = servicoAutor.getAuthorsAliveInYear(year);
        if (authors.isEmpty()) {
            System.out.printf("Nenhum autor encontrado vivo no ano %d.%n", year);
        } else {
            System.out.printf("Autores vivos no ano %d:%n", year);
            authors.forEach(author -> System.out.println(author));
        }
    }
}