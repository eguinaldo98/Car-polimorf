package models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import vehicles.Ambulance;
import vehicles.BlackSedan;
import vehicles.Bus;

public class Simulator extends JPanel implements ActionListener {

	private Image background;
	private PlayerCar player;
	private Timer timer;
	private List<EntityCar> entity;
	private List<Scenarios> build;
	private Road road1, road2;

	public Simulator() {
		setFocusable(true);// deixa a tela em foco para manter um framerate adequado
		setDoubleBuffered(true);// mantem um buffer dos itens já renderizados para reutilizar em funçoes de
								// pintura

		ImageIcon reference = new ImageIcon("assets/scenario/background.png");
		background = reference.getImage();

		player = new PlayerCar(2);
		road1 = new Road(0, 0);
		road2 = new Road(1024, 0);
		player.load();
		road1.load();
		road2.load();

		addKeyListener(new TecladoAdapter());
		timer = new Timer(15, this); // seta a velocidade do jogo e por consequencia a velocidade do carro
		timer.start();

		inicializeBuild();
		inicializeEntity();
	}

	public void inicializeEntity() {
		entity = new ArrayList<EntityCar>();
		int rand;
		for (int i = 0; i < 400; i++) {
			rand = (int) (Math.random() * 4);
			if (rand == 1) {
				int x = (int) (Math.random() * 400 + 1024 + (i * 500));// posição na qual o carro será gerado no eixo x														// e garante que eles nao serao sobrepostos
				int y = (int) (423); // garante que o carro seá gerado na mesma altura ficando sempre na pista
				EntityCar car = new Ambulance(x, y, 2);
				entity.add(car);// adiona o carro na lis
			}
			if (rand == 2) {
				int x = (int) (Math.random() * 400 + 1024 + (i * 500));// posição na qual o carro será gerado no eixo x														// e garante que eles nao serao sobrepostos
				int y = (int) (423); // garante que o carro seá gerado na mesma altura ficando sempre na pista
				EntityCar car = new Bus(x, y, 2);
				entity.add(car);// adiona o carro na lis
			}
			if (rand == 3) {
				int x = (int) (Math.random() * 400 + 1024 + (i * 500));// posição na qual o carro será gerado no eixo x														// e garante que eles nao serao sobrepostos
				int y = (int) (423); // garante que o carro seá gerado na mesma altura ficando sempre na pista
				EntityCar car = new BlackSedan(x, y, 2);
				entity.add(car);// adiona o carro na lis
			}
			if (rand == 4) {
				int x = (int) (Math.random() * 400 + 1024 + (i * 500));// posição na qual o carro será gerado no eixo x														// e garante que eles nao serao sobrepostos
				int y = (int) (423); // garante que o carro seá gerado na mesma altura ficando sempre na pista
				EntityCar car = new Ambulance(x, y, 2);
				entity.add(car);// adiona o carro na lis
			}
		}
	}

	public void inicializeBuild() {
		int BuildValue[] = new int[400];
		build = new ArrayList<Scenarios>();

		for (int i = 1; i <= BuildValue.length; i++) {
			int x = i * 300; // gera uma casa a cada 300 pixels
			int y = (int) (23);
			int j = (int) (Math.random() * 3);
			build.add(new Scenarios(x, y, j));
		}

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, 0, 0, null);// desenha o background na tela
		graficos.drawImage(road1.getImage(), road1.getX(), road1.getY(), this);// desenha uma das pistas na tela
		graficos.drawImage(road2.getImage(), road2.getX(), road2.getY(), this);// desenha a pista auxiliar

		if (entity.size() > 0) {
			for (int j = 0; j < entity.size(); j++) {
				EntityCar en = entity.get(j);
				en.load();// carrega as informaçoes graficas dos carros gerados
				graficos.drawImage(en.getImage(), en.getX(), en.getY(), this);// desenha os carros randomicos
			}
		}

		for (int j = 0; j < build.size(); j++) {
			Scenarios en = build.get(j);
			en.load();// carrega as informaçoes graficas das contruções
			graficos.drawImage(en.getImage(), en.getX(), en.getY(), this);// desenha uma construção
		}

		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);// o carro do jogador sempre deve ser
																					// desenhado por ultimo, para nao
																					// haver sobreposição dos demais
																					// elementos
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {// esta função atualiza a cada evento que ocorre no caso é baseado no
												// timer, e nos eventos do teclado
		player.update();// atualiza a posição do player
		road1.update();// atualiza a posição da estrada
		road2.update();// atualiza a posição da segunda estrada

		if (entity.size() > 0) {
			for (int j = 0; j < entity.size(); j++) {
				EntityCar en = entity.get(j);
				if (en.isVisible()) {
					en.update();// caso o carro gerado seja visivel na tela e ainda nao tenha passado pelo
								// player, ele atualiza a sua posição
				} else {
					entity.remove(j);// caso o carro seja gerado e ja tenha passado pela tela ele é removido
				}
			}
		}

		for (int j = 0; j < build.size(); j++) {
			Scenarios en = build.get(j);
			if (en.isVisible()) {
				en.update();// mesma lógica dos carros acima
			} else {
				build.remove(j);
			}

		}

		repaint();// redesenha os elementos na tela
	}

	private class TecladoAdapter extends KeyAdapter {// funçoes para capturar eventos do teclado
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_RIGHT) {// caso o jogador pressione a tecla da seta direita o jogo irá acelerar e o
											// carro se movera para frente
				timer.setDelay(15);
			}
			if (code == KeyEvent.VK_LEFT) {// caso o jogador pressione a tecla da seta da esquer o jogador irá reduzer a
											// velocidade e irá se mover para tras
				timer.setDelay(30);
			}
			if (code == KeyEvent.VK_0) {// caso o jogador pressione a tecla da seta da esquer o jogador irá reduzer a

			}

			// if (code == KeyEvent.VK_1) {// caso o jogador pressione a tecla da seta da
			// esquer o jogador irá reduzer a
			// // velocidade e irá se mover para tras
			// timer.setDelay(30);
			// }
			// if (code == KeyEvent.VK_2) {// caso o jogador pressione a tecla da seta da
			// esquer o jogador irá reduzer a
			// // velocidade e irá se mover para tras
			// timer.setDelay(30);
			// }

		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);

			int code = e.getKeyCode();
			if (code == KeyEvent.VK_RIGHT) {// caso nao pressione nenhuma tecla a velocidade do jogo se manterá em 20 e
											// o carr ficara em uma posição na tela
				timer.setDelay(20);
				;
			}
			if (code == KeyEvent.VK_LEFT) {// caso nao pressione nenhuma tecla a velocidade do jogo se manterá em 20 e o
											// carr ficara em uma posição na tela
				timer.setDelay(20);
				;
			}
			// if (code == KeyEvent.VK_0) {// caso o jogador pressione a tecla da seta da
			// esquer o jogador irá reduzer a
			// EntityCar car = new Ambulance(423, 1024,2); // velocidade e irá se mover para
			// tras
			// inicializeEntity(car);
			// }
		}
	}

}
