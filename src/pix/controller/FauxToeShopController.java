package pix.controller;

import pix.view.FauxToeShopFrame;
import pix.view.FauxToeShopPanel;

public class FauxToeShopController
{
	private FauxToeShopFrame baseFrame;
	private FauxToeShopPanel myPanel;
	
	public FauxToeShopController()
	{
		baseFrame = new FauxToeShopFrame(this);
	}
	
	public void start()
	{
		FauxToeShopPanel myPanel = (FauxToeShopPanel) baseFrame.getContentPane();
	}
}
