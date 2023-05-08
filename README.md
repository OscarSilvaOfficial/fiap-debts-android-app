# App de gestão de dívidas

O projeto é um aplicativo Android que permite que o usuário registre e gerencie suas dívidas. O aplicativo utiliza o banco de dados NoSQL em tempo real e na nuvem do Firebase Firestore para armazenar e recuperar informações sobre as dívidas.

## Funcionalidades

### O aplicativo possui as seguintes funcionalidades:

- Lista as dívidas registradas no banco de dados.
- Permite que o usuário adicione uma nova dívida.
- Permite que o usuário exclua dívidas existentes.
- Exibe o valor total das dívidas registradas.

## Simulação da Sprint usando Kanban

### Funcionalidades da Sprint

| A Fazer | Em Progresso | Feito |
| --- | --- | --- |
| Criar tela de login | Implementar autenticação com Firebase | Autenticação concluída |
| Criar tela principal com lista de contas | Implementar adição de contas | Adição de contas concluída |
| Criar tela de adição de conta | Implementar exclusão de contas | Exclusão de contas concluída |
| Configurar conexão com o Firebase Firestore | Implementar testes unitários | Testes unitários concluídos |

## Como rodar o projeto

- Abra o projeto no Android Studio.
- Selecione um dispositivo de destino.
- Clique no botão "Run" (ou "Run 'app'") na barra de ferramentas do Android Studio.
- Aguarde até que o aplicativo seja compilado e instalado no dispositivo de destino.

obs: Lembre-se de que o aplicativo depende do serviço Firebase Firestore e requer uma conexão com a internet para funcionar corretament

## Como rodar os testes

- Abra o projeto no Android Studio.
- Navegue até a pasta "app/src/test/java/com.fiap.twinttler".
- Clique com o botão direito na classe que deseja testar e selecione "Run 'NomeDoTeste'".

Isso iniciará a execução do teste selecionado e os resultados serão exibidos na janela de "Run" do Android Studio. 
Certifique-se de que o dispositivo de destino selecionado seja "Local JUnit Test". 
Você também pode executar todos os testes unitários ao mesmo tempo, clicando com o botão direito na pasta "com.fiap.twinttler" e selecionando "Run 'Tests in 'com.fiap.twinttler.

## Técnologias usadas:

- Android Studio: um ambiente de desenvolvimento integrado (IDE) para o desenvolvimento de aplicativos Android.
- Firebase Firestore: um banco de dados NoSQL em tempo real e na nuvem.
- Java: uma linguagem de programação de alto nível, orientada a objetos e amplamente usada para o desenvolvimento de aplicativos Android.
- Para executar o projeto, é necessário ter o ambiente de desenvolvimento Java, o Android Studio e a biblioteca do Firebase Firestore instalados no computador. É possível executar o aplicativo em um emulador ou dispositivo físico.
