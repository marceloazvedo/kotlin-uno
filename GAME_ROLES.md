# Uno em Kotlin

## Contexto

Durante o ínicio da pandemia de Covid-19 eu e os meus amigos procuramos alguns jogos *online* que nos ajudassem a
interagir virtualmente, e como nem todos da turma eram *gamers* nós resolvemos buscar jogos mais simples, como dama,
ludo e **uno**.
Porém, acabei não encontrando nenhum jogo que agradace, todos eram muito simples e geralmente não tinham a opção de
criar salas, convidar pessoas, para possíbilitar assim ser possível jogar com os meus amigos e foi aí que eu resolvi
tentar criar o meu próprio jogo de **Uno**.
Felizmente (ou infelizmente para a criação desse jogo) acabamos encontrando outros jogos divertidos e assim com o trabalho e a "desopilação" desses
jogos eu acabei despriorizando a criação desse meu jogo de **Uno**.

Então recentemente pensando em aprimorar os meus conhecimentos de padrões de projeto de *software* eu resolvi reavivar a
minha vontade de **criar um jogo de Uno** que surgiu durante a pandemia, para tentar adicionar nesse projeto os mais
variados padrões e assim conseguir aprender a melhor forma de emprega-los no meu dia a dia.

A princípio, esse jogo será em terminal e posteriomente podendo ser criada a interface Web
para dar suporte ao jogo com multijogadores.

## Cartas, regras e nomenclaturas

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

- [x] O jogador inicia com 6 cartas;
- [x] O primeiro jogador joga a carta que inicia o jogo;
- [x] A próxima carta a ser jogada tem que ter a mesma cor **ou** o mesmo valor;
  
  - Ex.: Se tem uma carta de valor 2 e vermelha "na mesa", o próximo jogador pode jogar ou um dois ou uma carta da cor vermelha.
- [ ] Um jogador pode "cortar" a vez de outro caso tenha uma carta igual (mesma cor e valor) da que está "na mesa";
- [x] Algumas cartas têm habilidades especiais que podem mudar o curso do jogo;
- [ ] O jogador que tiver apenas uma carta precisar "gritar Uno" antes de jogar a carta final, caso outro jogador grite uno antes dele, ele precisa pegar mais uma carta;

  - Provavelmente só será possível implementar isso na interface Web.
- [ ] Ganha o jogador que conseguir jogar todas as cartas "na mesa".

### Habilidades especiais já implementadas

- [x] Carta de inversão de sentido;
- [x] Carta de +2;
- [x] Carta de bloqueio de próximo jogador;
- [x] Carta de joker para troca de cor;
- [x] Carta de joker troca de cor e +4.

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

As cartas devem seguir o seguinte padrão:
```
_____
| Vc|
_____
```
Onde `V` é **sempre maísculo** e representa o valor da carta. Contempla os seguintes valores:

- `0 a 9`: Números qualquer, (por enquanto) sem nenhuma habilidade especial;
- `R`: Carta de inversão de sentido;
- `+2`: Carta de +2;
- `B`: Carta de Pular;
- `J`: Carta de joker para troca de cor;
- `+4`: Carta de joker para troca de cor e +4.

O `c` é sempre minusculo e representa as cores, onde temos as 4 mais 1 que represtenta cor nenhuma. São elas:

- `r`: Vermelho (red);
- `g`: Verde (green);
- `b`: Azul (blue);
- `y`: Amarelo (yellow);
- `x`: Sem cor.

**Exemplo:**

Na mão desse jogador nós temos a seguinte saída no terminal:
```
  1  |  2  |  3  |  4  |  5  |  6  
_____ _____ _____ _____ _____ _____
| Ry| | Jx| |+4x| |+2r| | 8b| | Bb|
_____ _____ _____ _____ _____ _____
```
Respectivamente temos as seguintes cartas:

1. Carta de inversão de sentido da cor amarela;
2. Carta de joker para troca de cor;
3. Carta de joker para troca de cor e +4;
4. Carta de +2 da cor vermelha;
5. Carta 8 da cor azul;
6. Carta de pular da cor azul.