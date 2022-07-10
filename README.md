# Uno em Kotlin

Esse projeto tem como objetivo criar um jogo de Uno na linguagem de programação Kotlin
com o objetivo de aplicar **padrões de projeto de software**.

A princípio, esse jogo será em terminal e posteriomente podendo ser criada a interface Web
para dar suporte ao jogo com multijogadores.

### Distribuição das cartas

- 19 Cartas azuis, numeradas de 0 a 9;
- 19 Cartas verdes, numeradas de 0 a 9;
- 19 Cartas amarelas, numeradas de 0 a 9;
- 19 Cartas vermelhas, numeradas de 0 a 9;
- 8 Cartas de inversão de sentido (duas de cada cor);
- 8 Cartas de +2 (duas de cada cor);
- 8 Cartas de Pular (duas de cada cor);
- 4 Cartas de joker para troca de cor;
- 4 Cartas de joker troca de cor e +4.

## Funcionalidades e regras
Abaixo segue algumas das funcionalidades e regras desse jogo. As que estiverem marcadas é que já forma implementadas.

- [ ] O jogador inicia com 6 cartas;
- [ ] O primeiro jogador joga a carta que inicia o jogo;
- [ ] A próxima carta a ser jogada tem que ter a mesma cor **ou** o mesmo valor;
  
  - Ex.: Se tem uma carta de valor 2 e vermelha "na mesa", o próximo jogador pode jogar ou um dois ou uma carta da cor vermelha.
- [ ] Um jogador pode "cortar" a vez de outro caso tenha uma carta igual (mesma cor e valor) da que está "na mesa";
- [ ] Algumas cartas têm habilidades especiais que podem mudar o curso do jogo;
- [ ] O jogador que tiver apenas uma carta precisar "gritar Uno" antes de jogar a carta final, caso outro jogador grite uno antes dele, ele precisa pegar mais uma carta;

  - Provavelmente só será possível implementar isso na interface Web.
- [ ] Ganha o jogador que conseguir jogar todas as cartas "na mesa".

### Habilidades especiais já implementadas

- [ ] Carta de inversão de sentido;
- [ ] Carta de +2;
- [ ] Carta de Pular;
- [ ] Carta de joker para troca de cor;
- [ ] Carta de joker troca de cor e +4.

### Habilidades especiais das cartas

- **Carta de inversão de sentido:** Se o sentido do jogo estiver da **direita -> esquerda**,
ao jogar essa carta o sentido do jogo é intertido para o sentido **esquerda -> direita**;
- **Carta de +2**: Ao jogar essa carta o próximo jogador receberá duas cartas e perde a vez.
Caso esse próximo jogador tenha uma carta de valor (+2) igual poderá joga-lá e o efeito dessa carta é acumulado para o próximo jogador,
fazendo com que ele pegue 4 cartas. Essa regra também vale para todos os outros jogadores consequêntes;
- **Carta de Pular**: Esta carta pula a vez do jogador próximo jogador. Mesmo que o jogador tenha uma carta igual a esta na mão,
terá de cumprir o castigo. Esta carta só pode ser jogada caso esteja na mesa uma carta com a mesma cor ou com valor igual;
- **Carta de joker para troca de cor**: Na vez do jogador que tem essa carta ele poderá jogar acima de qualquer carta
e escolher a cor que o próximo jogador deve jogar;
- **Carta de joker para troca de cor e +4**: Tem o mesmo efito da carta de Joker para troca de cor com o acrescimo de que
o próximo jogador pega 4 cartas e perde a vez. Se o próximo jogador tiver uma carta igual poderá jogar e transferir o
efeito dessa carta para o próximo jogador que deverá pegar 8 cartas (2 vezes 4+), assim sucessivamente para os próximos.

### Nomenclatura das cartas no terminal

A baixo teremos como ficará o "desenho" de cada carta no terminal, assim o jogador conseguirá identificar qual carta ao 
jogador tem na mão e jogou.

> TODO: Ainda por fazer.