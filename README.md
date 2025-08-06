# Processador de Dados XML

# 1. Visão Geral

Esta é uma aplicação web desenvolvida em Java com o framework Spring Boot. Sua principal finalidade é permitir a importação de dados de Produtos e Clientes a partir de um arquivo XML para um banco de dados.

Além da importação, a aplicação oferece uma interface para visualizar os dados importados e gerenciar o cadastro de clientes (Adicionar, Editar e Excluir).

# 2. Funcionalidades Principais

   Upload de Arquivo XML: Importa dados de produtos e clientes de uma só vez.

   Prevenção de Duplicatas: Durante a importação, o sistema verifica se um produto (pelo nome) ou cliente (pelo e-mail) já existe, evitando a inserção de registros duplicados.

   Consulta de Dados: Exibe listas paginadas de todos os produtos e clientes cadastrados no sistema.

   Gerenciamento de Clientes (CRUD):

        Criar: Adicionar novos clientes através de um formulário web.

        Editar: Atualizar as informações de clientes existentes.

        Excluir: Remover clientes do banco de dados.

   Exclusão de Produtos: Permite a remoção de produtos diretamente pela página de consulta.

# 3. Como Utilizar a Aplicação

A aplicação possui uma navegação simples e intuitiva. Siga os passos abaixo para utilizar as funcionalidades.

3.1. Página Inicial

Ao acessar a raiz da aplicação, você encontrará a página principal, que oferece duas opções de navegação:

    Upload de XML: Para importar dados.

    Consultar Dados: Para visualizar e gerenciar os registros.

Qualquer mensagem de sucesso ou erro após uma operação (como o upload de um arquivo) será exibida nesta página.

3.2. Importando Dados via XML

Esta é a principal funcionalidade para popular o banco de dados de forma massiva.

    Na página inicial, clique em "Upload de XML".

    Na página de upload, clique no botão "Escolher arquivo" e selecione o arquivo XML em seu computador.

    Importante: O arquivo XML deve seguir a estrutura especificada na seção 4 deste documento. Arquivos com formato inválido ou que não sejam .xml serão rejeitados.

    Clique em "Enviar".

    Você será redirecionado para a página inicial, onde uma mensagem informará se o processamento foi bem-sucedido ou se ocorreu algum erro.

3.3. Consultando e Gerenciando os Dados

    Na página inicial, clique em "Consultar Dados".

    Você será levado a uma página que exibe duas tabelas: uma para Produtos e outra para Clientes.

    Para Clientes:

        Adicionar: Clique no botão "Adicionar Novo Cliente" localizado acima da tabela de clientes. Preencha o formulário e clique em "Salvar".

        Editar: Ao lado de cada cliente na lista, clique no botão "Editar". Modifique os dados no formulário e clique em "Salvar".

        Excluir: Ao lado de cada cliente, clique no botão "Excluir" para remover o registro.

    Para Produtos:

        Excluir: Ao lado de cada produto na lista, clique no botão "Excluir" para remover o registro.

# 4. Estrutura do Arquivo XML

Para que a importação de dados funcione corretamente, o arquivo XML deve seguir estritamente a estrutura abaixo. O elemento raiz deve ser <DadosEmpresa>, contendo as seções <Produtos> e <Clientes>.

Exemplo de arquivo dados.xml válido:
XML

<DadosEmpresa>
    <Produtos>
        <Produto>
            <nome>Notebook Gamer Pro</nome>
            <descricao>Notebook com placa de vídeo dedicada e 16GB RAM.</descricao>
            <preco>7500.00</preco>
            <categoria>Eletrônicos</categoria>
            <estoque>50</estoque>
        </Produto>
        <Produto>
            <nome>Smartphone X1</nome>
            <descricao>Smartphone com câmera de 108MP e tela AMOLED.</descricao>
            <preco>3200.50</preco>
            <categoria>Celulares</categoria>
            <estoque>120</estoque>
        </Produto>
    </Produtos>
    <Clientes>
        <Cliente>
            <nome>Ana Silva</nome>
            <email>ana.silva@example.com</email>
            <endereco>Rua das Flores, 123, São Paulo, SP</endereco>
            <telefone>(11) 98765-4321</telefone>
        </Cliente>
        <Cliente>
            <nome>Carlos Pereira</nome>
            <email>carlos.p@example.com</email>
            <endereco>Avenida Principal, 456, Rio de Janeiro, RJ</endereco>
            <telefone>(21) 91234-5678</telefone>
        </Cliente>
    </Clientes>
</DadosEmpresa>

Observações:

    A tag <id> não é necessária no XML, pois o sistema gera o ID automaticamente no banco de dados.

    Todas as tags dentro de <Produto> e <Cliente> são obrigatórias, com exceção da <descricao> do produto.

# 5. Endpoints da API (Rotas)

A aplicação expõe as seguintes rotas:
Verbo HTTP	Rota	Descrição
GET	/	Exibe a página inicial.
GET	/upload	Exibe a página de upload de arquivo.
POST	/upload	Processa o upload do arquivo XML.
GET	/consulta	Exibe a página com as listas de produtos e clientes.
GET	/cliente/novo	Exibe o formulário de criação de cliente.
GET	/cliente/editar/{id}	Exibe o formulário de edição para um cliente.
POST	/cliente/salvar	Salva um cliente novo ou atualizado.
POST	/cliente/deletar/{id}	Deleta um cliente específico. (Usado no formulário)
POST	/consulta/delete/produto/{id}	Deleta um produto específico. (Usado na página de consulta)
POST	/consulta/delete/cliente/{id}	Deleta um cliente específico. (Usado na página de consulta)

# 6. Tecnologias Utilizadas

   Backend:

        Java

        Spring Boot

        Spring Web

        Spring Data JPA (Hibernate)

        JAXB (para processamento de XML)

   Frontend:

        Thymeleaf (motor de templates)

        HTML / Bootstrap

   Banco de Dados:

        Compatível com qualquer banco de dados SQL suportado pelo Spring Data JPA (ex: H2, PostgreSQL, MySQL).
