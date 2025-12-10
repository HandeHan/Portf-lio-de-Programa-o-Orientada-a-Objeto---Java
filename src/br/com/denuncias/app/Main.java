package br.com.denuncias.app;

import br.com.denuncias.model.Usuario;
import br.com.denuncias.service.UsuarioService;
import br.com.denuncias.service.DenunciaService;
import br.com.denuncias.repository.UsuarioRepository;
import br.com.denuncias.repository.DenunciaRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository());
        DenunciaService denunciaService = new DenunciaService(new DenunciaRepository());

        System.out.println("=== Sistema de Denúncias ===");

        while (true) {
            System.out.println("\n1 - Login");
            System.out.println("2 - Cadastrar Usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            if (op == 0) break;

            if (op == 2) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Senha: ");
                String senha = sc.nextLine();

                System.out.print("É administrador? (s/n): ");
                boolean admin = sc.nextLine().equalsIgnoreCase("s");

                usuarioService.cadastrar(nome, email, senha, admin);
                System.out.println("Usuário cadastrado.");
                continue;
            }

            if (op == 1) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Senha: ");
                String senha = sc.nextLine();

                Usuario usuario = usuarioService.login(email, senha);

                if (usuario == null) {
                    System.out.println("Credenciais inválidas.");
                    continue;
                }

                if (usuario.isAdministrador()) {
                    menuAdministrador(sc, denunciaService);
                } else {
                    menuDenunciante(sc, denunciaService, usuario);
                }
            }
        }
    }

    private static void menuAdministrador(Scanner sc, DenunciaService service) {
        while (true) {
            System.out.println("\n=== Menu Administrador ===");
            System.out.println("1 - Listar denúncias");
            System.out.println("2 - Resolver denúncia");
            System.out.println("0 - Logout");

            int op = Integer.parseInt(sc.nextLine());

            if (op == 0) break;

            if (op == 1) {
                service.listar().forEach(d ->
                        System.out.println(d.getId() + " | " + d.getDescricao() + " | " + d.getStatus()));
            }

            if (op == 2) {
                System.out.print("ID da denúncia: ");
                int id = Integer.parseInt(sc.nextLine());

                if (service.resolver(id)) {
                    System.out.println("Denúncia resolvida.");
                } else {
                    System.out.println("Não foi possível resolver.");
                }
            }
        }
    }

    private static void menuDenunciante(Scanner sc, DenunciaService service, Usuario usuario) {
        while (true) {
            System.out.println("\n=== Menu Denunciante ===");
            System.out.println("1 - Registrar denúncia");
            System.out.println("0 - Logout");

            int op = Integer.parseInt(sc.nextLine());

            if (op == 0) break;

            if (op == 1) {
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                service.registrar(usuario.getId(), descricao);
                System.out.println("Denúncia registrada.");
            }
        }
    }
}