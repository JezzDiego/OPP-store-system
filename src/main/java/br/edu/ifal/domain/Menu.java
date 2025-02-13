package br.edu.ifal.domain;

import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.dao.ItemPedidoDao;
import br.edu.ifal.dao.PedidoDao;
import br.edu.ifal.dao.ProdutoDao;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int option = getValidOption(scanner);

            switch (option) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    cadastrarCliente();
                    break;

                case 3:
                    buscarProduto();
                    break;

                case 4:
                    listarProdutos();
                    break;

                case 5:
                    efetuarVenda();
                    break;

                case 6:
                    listarVendas();
                    break;

                case 0:
                    sair();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Cadastrar cliente");
        System.out.println("3. Buscar produto");
        System.out.println("4. Listar todos os produtos");
        System.out.println("5. Efetuar venda");
        System.out.println("6. Listar vendas realizadas");
        System.out.println("0. Sair");
        System.out.print("Digite sua opção: ");
    }

    private static void cadastrarProduto() {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o nome do produto: ");
        String name = s.nextLine();

        System.out.println("Digite o valor unitário do produto: ");
        double unitValue = s.nextDouble();

        System.out.println("Digite a quantidade do produto: ");
        double quantity = s.nextDouble();

        try {
            Produto p = new Produto(1, name, unitValue, quantity);
            new ProdutoDao().save(p);

            System.out.printf("Produto %s cadastrado com sucesso.\n", p.getNome());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void cadastrarCliente() {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o nome do CPF: ");
        String CPF = s.nextLine();

        System.out.println("Digite o nome: ");
        String name = s.nextLine();

        System.out.println("Digite o endereço: ");
        String address = s.nextLine();

        System.out.println("Digite o telefone: ");
        String phoneNumber = s.nextLine();

        try {
            Cliente c = new Cliente(CPF, name, address, phoneNumber);
            new ClienteDao().save(c);

            System.out.printf("Cliente %s cadastrado com sucesso.\n", c.getNome());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarProduto() {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o id do produto que deseja: ");
        int id = s.nextInt();

        try {
            Produto p = new ProdutoDao().findById(id);

            System.out.println(p.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void listarProdutos() {
        try {
            List<Produto> p = new ProdutoDao().findAll();

            p.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void efetuarVenda() {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o CPF do cliente: ");
        String clientCPF = s.nextLine();

        System.out.println("Digite o CPF do funcionario: ");
        String funcCPF = s.nextLine();

        System.out.println("Digite o valor total da venda: ");
        double totalValue = s.nextDouble();

        int pedidoId = new PedidoDao().save(new Pedido(1, clientCPF, funcCPF, totalValue));

        while (true) {
            System.out.println("Qual o ID do produto que deseja comprar?");
            int id = s.nextInt();

            System.out.println("Quantidade desejada?");
            int quantity = s.nextInt();

            try {
                Produto produto = new ProdutoDao().findById(id);

                if (produto.getQuantidade() >= quantity) {
                    produto.setQuantidade(produto.getQuantidade() - quantity);
                    new ProdutoDao().update(produto);
                } else {
                    System.out.println("Quantidade insuficiente para essa compra.");
                    continue;
                }

                ItemPedido itemPedido = new ItemPedido(1, pedidoId, produto.getId(), quantity, produto.getValorUnit());
                new ItemPedidoDao().save(itemPedido);

                System.out.println("Produto adicionado ao pedido. Deseja adicionar outro produto? (s/n)");
                s.nextLine();
                String option = s.nextLine().trim().toLowerCase();
                if (!option.equalsIgnoreCase("s")) {
                    break;
                }

                System.out.println("Venda efetuada com sucesso.");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void listarVendas() {
        try {
            List<Pedido> p = new PedidoDao().findAll();

            p.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void sair() {
        System.out.println("Encerrando o programa.");
    }

    private static int getValidOption(Scanner scanner) {
        int option;
        while (true) {
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira um número: ");
            }
        }
        return option;
    }
}