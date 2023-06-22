# tranca-api
Projeto de gerenciamento de condominio feito na Seeds Tecnologia

projeto tem como base no gerenciamento em um condominio tendo sistema de CRUD em usuarios, espaços, convidados,  reserva de espaço e condominios que fazem parte da empresa
A api ja esta documentada com o Swagger ainda nao foi feito no deploy pelo fato que antes eu tinha feito o deploy no heroku, mas a plataforma parou de oferecer serviços gratuitos de hospedagem
A api tem sistema de autenticação por JWT (token) e sistema de hieraquia de autenticação (ADM, ADM condominio, Funcionario e usuario(morador) cada crud so tem acesso com determinado perfil
o Design Patters usado foram o Repository, DTO e MVC.
O projeto ultiliza a arquitetura RESTful.
Futuramente vou implementar sistema PUSH e Email na API e começar a fazer o Front end dele no Angular
