# Projeto de Programação de Dispositivos Móveis

## UBI5stars: Locais Impecáveis a Visitar na Covilhã

### Contextos e Objetivos

Os principais objetivos deste trabalho são desenhar e prototipar um sistema (ou uma aplicação, na versão mais básica) que mostra aos seus utilizadores os locais a visitar na cidade da Covilhã. O mapa dos monumentos que devem estar disponíveis no protótipo desenvolvido encontra-se em anexo ao documento de propostas. Enquanto que a versão mais simples pode simplesmente mostrar a figura fornecida estaticamente (i.e., sem interatividade) e apresentar os locais a visitar em forma de lista de texto, a versão mais elaborada deste sistema deve permitir total interatividade sobre esse mapa, mostrando informações e fotografias dos locais quando o utilizador clica no lugar respetivo no mapa. Enquanto que a versão mais simples pod ser constituída por uma aplicação isolada (e portanto com uma base de dados local), a versão mais avançada (e preferida para este trabalho) deve ter uma base de dados remota, gerida por um administrador, que pode alterar informações acerca de locais, comentários deixados para um monumento, ou eventualmente até aceitar a adição de mais locais (e.g., configurando as coordenadas GPS).

### Funcionalidades

O conjunto base de funcionalidades a implementar enuncia-se a seguir:

1. A aplicação deve mostrar o mapa de todos os monumentos a visitar na Covilhã (fornecido com a proposta), mesmo que sem interatividade;
2. A aplicação deve mostrar informação acerca de todos os monumentos identificados (a informação pode ser pedida ao proponente (a informação deve ser guardada numa base de dado);
3. A aplicação deve suportar a introdução de um comentário e uma classificação (até 5 estrelas) para cada um dos monumentos/lugares mostrados (mesmo que fiquem guardados localmente; a base de dados deve estar desenhada de forma adequada para suportar esta funcionalidade);
4. Deve ser possível despoletar a aplicação de mapas do sistema operativo (e.g., Google Maps) a partir da perspetiva que dá informação de cada documento, sendo que a aplicação de mapas fica imediatamente configurada para mostrar o caminho entre o ponto atual e o monumento/lugar;
5. Deve ser possível pesquisar por nome de monumento e tipo de espaço (e.g., procurar por Monumentos; Museus; Arte Urbana; Espaços de Lazer; Zonas comerciais; Zona Desportivas; Escolas; Transportes);
6. Deve ser adicionado suporte a publicidade, ainda que possa ser fixa e venha por defeito (a base de dados deve estar desenhada de forma adequada para suportar esta funcionalidade).

Uma versão mais elaborada da aplicação pode conter as funcionalidades seguintes:

1. O mapa com os monumentos/locais é a perspetiva principal e totalmente interativo;
2. A aplicação comunica com uma base de dados remota para atualizar a sua base de dados local;
3. Os comentários e classificações são enviados para uma base de dados remota e usadas para calcular uma classificação média para cada lugar/monumento a todos os utilizadores;
4. É criada uma interface web (ou outra) de administração da base de dados remota (e.g., para curar/remover comentários ou classificações);
5. A interface de administração (ver ponto anterior) permite a configuração de publicidade, que é enviada para os utilizadores da aplicação na próxima vez que a ligarem (e tiverem rede).
6. Outras funcionalidades que considere interessantes.