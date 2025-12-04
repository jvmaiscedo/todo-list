# language: pt
Funcionalidade: Unicidade de Tarefas
  Como um usuário organizado
  Eu não quero ter tarefas duplicadas na minha lista
  Para evitar confusão sobre o que já foi feito

  Cenário: Tentar criar uma tarefa que já existe
    Dado que o usuário já possui uma tarefa com título "Estudar BDD"
    Quando ele tenta criar outra tarefa com o título "Estudar BDD"
    Então o sistema não deve criar a nova tarefa
    E deve retornar uma mensagem de erro ou nulo