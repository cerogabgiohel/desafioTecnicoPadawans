Desafio Técnico Padawans

Projeto desenvolvido para o processo seletivo Programa de Estágio 1º/2021 - framework padawans.
Aplicação em Java que realiza a exibição de três telas com conteúdos da API publicada em https://jsonplaceholder.typicode.com/
Para o desenvolvimento foram utilizados o JavaFX SDK 11.0.2 e o json-20201115.
Todos esses jars estão disponíveis no último commit, na pasta libraries, localizada dentro da pasta src (os jars do JavaFX SDK 11.0.2 estão dentro da pasta lib,
que está dentro da pasta openjfx-11.0.2_windows-x64_bin-sdk/javafx-sdk-11.0.2), por conta dessas bibliotecas o arquivo ficou grande.
Para rodar o código é necessário adicionar todos esses jars e digitar no campo VM arguments o seguinte texto:
--module-path="C:\Users\exemplo\frameworkProject\src\Libraries\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib"--add-modules=javafx.fxml --add-modules=javafx.controls
