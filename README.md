# AuthApi

Este projeto é uma aplicação web com acesso restrito, fornecendo endpoints REST para registro e consulta de usuários. A aplicação é construída usando o ecossistema Spring para camadas de controle, segurança e inversão de controle (IoC). O frontend é desenvolvido usando Vue.js.

## Endpoints da API Backend

1. **Login do Usuário:** `POST /user/login`
   - Este endpoint é usado para o login do usuário. Os campos obrigatórios são email e senha.

2. **Registro de Usuário:** `POST /user/register`
   - Este endpoint é usado para registrar um novo usuário. Os campos obrigatórios são email, senha e função.

3. **Consulta de Usuários:** `GET /user`
   - Este endpoint é usado para recuperar uma lista de usuários. Ele suporta paginação.

4. **Verificar se o Email Existe:** `GET /user/existsByEmail/{email}`

## Rotas do Frontend

1. **Login:** `/login`
    - Esta rota exibe o formulário de login.

2. **Registro:** `/register`
    - Esta rota exibe o formulário de registro de usuário.

3. **Lista de Usuários:** `/users`
    - Esta rota exibe a lista de usuários caso o usu'ario seja um admin.

## Executando a Aplicação

Para executar a aplicação, siga estas etapas:

1. Inicie a aplicação Spring Boot (Run config IntelliJ inclusa)
2. Navegue até o diretório do projeto Vue.js e execute `npm install` para instalar as dependências.
3. Execute `npm run serve` para iniciar o servidor de desenvolvimento Vue.js.

## Por que Vue.js?

Vue.js foi escolhido como o framework de frontend por várias razões:

1. **Facilidade de Uso:** Vue.js tem uma curva de aprendizado suave, tornando-o uma ótima escolha para projetos que precisam estar funcionando rapidamente.

2. **Flexibilidade:** Vue.js é flexível e modular, permitindo uma configuração altamente personalizável que pode ser adaptada às necessidades específicas do projeto.

3. **Performance:** Vue.js é leve e rápido, o que pode levar a uma experiência de usuário mais suave.

4. **Comunidade e Ecossistema:** Vue.js tem uma comunidade forte e um rico ecossistema de bibliotecas e ferramentas, que podem ajudar a resolver problemas comuns e acelerar o desenvolvimento.

## Outras Possibilidades para o Frontend

1. **React:**
    - **Prós:** React é conhecido por sua eficiência e flexibilidade. Ele permite a criação de componentes reutilizáveis, o que pode acelerar o desenvolvimento. Além disso, tem uma grande comunidade e um rico ecossistema de bibliotecas e ferramentas.
    - **Contras:** O React é apenas uma biblioteca para construir interfaces de usuário, então você pode precisar de bibliotecas adicionais para funcionalidades como roteamento e gerenciamento de estado. Além disso, a estrutura de componentes do React pode levar a uma maior complexidade do código para projetos maiores.

2. **Angular:**
    - **Prós:** Angular é um framework completo, com muitos recursos integrados, como roteamento, gerenciamento de estado e formulários. Ele também suporta TypeScript nativamente, o que pode melhorar a qualidade do código e a produtividade do desenvolvedor.
    - **Contras:** Angular pode ser excessivamente complexo para projetos pequenos. Além disso, o Angular não é tão popular ou amplamente adotado como React ou Vue.js, o que pode resultar em menos recursos de aprendizado e suporte da comunidade.

3. **Svelte:**
    - **Prós:** Svelte compila o código para JavaScript puro no momento da construção, resultando em um código de frontend mais rápido e eficiente. Ele também tem uma sintaxe simples e limpa, o que pode tornar o código mais legível e fácil de manter.
    - **Contras:** Svelte é relativamente novo e não tão amplamente adotado como React, Vue.js ou Angular. Isso pode resultar em menos bibliotecas e ferramentas disponíveis e uma comunidade menor. Além disso, como Svelte não é tão popular, pode haver menos oportunidades de trabalho para desenvolvedores Svelte em comparação com React ou Angular.
   
