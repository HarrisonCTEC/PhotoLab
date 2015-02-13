package pix.view;

import javax.swing.JFrame;

import pix.controller.FauxToeShopController;

public class FauxToeShopFrame extends JFrame
{
	private FauxToeShopPanel basePanel;
	//private FauxToeShopMenu baseMenu;
	private FauxToeShopController baseController;
	
	public FauxToeShopFrame(FauxToeShopController baseController)
	{
		setTitle("FauxToeShop");
		this.baseController = baseController;
		basePanel = new FauxToeShopPanel(baseController);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setSize(700, 600);
		this.setContentPane(basePanel);
		this.setVisible(true);
	}

}
