#ATENÇÃO: Devido à natureza da API pública, existe um limite máximo de 60 requisições por hora!

##Instruções de Uso
Execute o aplicativo normalmente. O aplicativo inicia na tela exibindo uma lista de 100 usuários do GitHub.
A página principal possui uma barra de pesquisa totalmente funcional online. Você pode buscar qualquer usuário do GitHub inserindo o nome de usuário. Ao selecionar um usuário e clicar nele, o aplicativo o direcionará para a tela de detalhes.
Na tela de detalhes, você encontrará informações básicas do usuário e uma lista de seus repositórios.

##Tecnologias Utilizadas
Kotlin: O aplicativo é desenvolvido inteiramente em Kotlin.
Jetpack Compose: Utilizado para construir o layout da interface.
Hilt: Framework de injeção de dependência utilizado para gerenciar as dependências.
Retrofit: Utilizado para fazer chamadas de API REST.
coroutines, Flow, suspend functions e higher-order functions: Utilizados para programação assíncrona e gerenciamento de fluxo de dados.
Loading e tratamento de erros: O aplicativo inclui telas de carregamento e estados de erro para melhorar a experiência do usuário.

##Observações Gerais
A API pública do GitHub limita as requisições a 60 por hora, o que restringe algumas funcionalidades do aplicativo:
A lista de usuários não suporta paginação.
A função de busca só é acionada após clicar em "Enviar".
A lista de repositórios não possui paginação detalhada.
Uma notificação no aplicativo alerta os usuários quando o limite de requisições é atingido.
