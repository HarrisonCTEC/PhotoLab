package pix.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import pix.controller.FauxToeShopController;
import pixLab.classes.ImageDisplay;
import pixLab.classes.Picture;

public class FauxToeShopPanel extends JPanel
{
	private FauxToeShopController baseController;
	
	private JComboBox filterBox;
	private JScrollPane imagePane;
	private SpringLayout baseLayout;
	
	private Picture basePicture;
	
	public FauxToeShopPanel(FauxToeShopController baseController)
	{
		this.baseController = baseController;
		
		filterBox = new JComboBox();
		imagePane = new JScrollPane();
		basePicture = new Picture();
		baseLayout = new SpringLayout();
		
		basePicture = new Picture("beach.jpg");

		setupPicture();
		setupComboBox();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPicture()
	{
		BufferedImage bufferedPic = basePicture.getBufferedImage();
		ImageDisplay picDisplay = new ImageDisplay(bufferedPic);
		imagePane.setViewportView(picDisplay);
	}
	
	private void setupComboBox()
	{
		String [] filterArray = {"Default", "RED", "Mirror!", "zero blue"};
		
		filterBox = new JComboBox(filterArray);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(filterBox);
		this.add(imagePane);
		this.add(imagePane);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, filterBox, 25, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, filterBox, 199, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, imagePane, 70, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, imagePane, 10, SpringLayout.WEST, this);

	}
	
	private void setupListeners()
	{
		filterBox.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent currentEvent)
			{
				basePicture = new Picture("beach.jpg");
				switch(filterBox.getSelectedIndex()) {
				case 0: break;
				case 1: basePicture.setAllPixelsToAColor(Color.RED); break;
				case 2: basePicture.mirrorVertical(); break;
				case 3: basePicture.zeroBlue(); break;
				}
				setupPicture();
				
			}
			
		});
	}
}
