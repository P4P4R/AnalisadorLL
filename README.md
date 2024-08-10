# Parser LL(1) em Java

Este projeto é uma implementação de um analisador sintático LL(1) em Java, que processa expressões aritméticas simples. O analisador utiliza uma tabela de análise (parsing table) definida em um arquivo CSV para guiar o processo de análise.

## Estrutura do Projeto

- `Lexer.java`: Tokeniza a entrada, identificando números, identificadores, operadores, parênteses, etc.
- `ParsingTable.java`: Carrega a tabela de análise sintática de um arquivo CSV.
- `Parser.java`: Implementa o algoritmo LL(1), utilizando a tabela de análise e a pilha.
- `InputLoader.java`: Carrega o arquivo de entrada para ser analisado.
- `resources/parsing_table.csv`: Arquivo CSV contendo a tabela de análise sintática.
- `resources/input.txt`: Exemplo de arquivo de entrada que será analisado pelo programa.

## Como Executar

1. Certifique-se de que todos os arquivos estão corretamente configurados no projeto.
2. Execute o projeto através do IntelliJ IDEA ou de um terminal utilizando o comando `java`.

## Configuração de Desenvolvimento

- **Java Version**: 22.0.1 (ou a versão compatível que você está usando).
- **IDE**: IntelliJ IDEA 2024.1.3 (ou a versão mais recente que você está usando).

## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novas funcionalidades. Para isso, faça um fork do projeto e envie um pull request.

## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
