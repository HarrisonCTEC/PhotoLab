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
	private JComboBox fileBox;
	private JScrollPane imagePane;
	private SpringLayout baseLayout;
	
	String [] fileArray = {"beach.jpg", "temple.jpg", "water.jpg", "greenscreen.jpg"};
	
	private String basePictureFile = "beach.jpg";
	
	private Picture basePicture;
	
	public FauxToeShopPanel(FauxToeShopController baseController)
	{
		this.baseController = baseController;
		
		filterBox = new JComboBox();
		fileBox = new JComboBox();
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
		String [] filterArray = {"Default", "zero blue", "Keep only blue", "Keep only red", "Keep only green", "Negative", "Grayscale", "Fix underwater", "Mirror vertical", "Mirror vertical right to left", "Mirror horizontal", "Mirror horizontal bottom to top", "Mirror diagonal", "Chromakey"};
		filterBox = new JComboBox(filterArray);
		baseLayout.putConstraint(SpringLayout.NORTH, filterBox, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, filterBox, 10, SpringLayout.WEST, this);
		
		fileBox = new JComboBox(fileArray);
		baseLayout.putConstraint(SpringLayout.NORTH, fileBox, 6, SpringLayout.SOUTH, filterBox);
		baseLayout.putConstraint(SpringLayout.WEST, fileBox, 0, SpringLayout.WEST, filterBox);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(filterBox);
		this.add(fileBox);
		this.add(imagePane);
		this.add(imagePane);
	}
	
	private void setBasePicture()
	{
		setBasePicture(basePictureFile);
	}
	
	private void setBasePicture(String selectedFile)
	{
		basePicture = new Picture(selectedFile);
		basePictureFile = selectedFile;
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, imagePane, 70, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, imagePane, 10, SpringLayout.WEST, this);

	}
	
	private void changeEffect(int index)
	{
		setBasePicture();
		switch(index) {
		case 0: break;
		case 1: basePicture.zeroBlue(); break;
		case 2: basePicture.keepOnlyBlue(); break;
		case 3: basePicture.keepOnlyRed(); break;
		case 4: basePicture.keepOnlyGreen(); break;
		case 5: basePicture.negate(); break;
		case 6: basePicture.grayscale(); break;
		case 7: basePicture.fixUnderwater(); break;
		case 8: basePicture.mirrorVertical(); break;
		case 9: basePicture.mirrorVerticalRightToLeft(); break;
		case 10: basePicture.mirrorHorizontal(); break;
		case 11: basePicture.mirrorHorizontalBottomToTop(); break;
		case 12: basePicture.mirrorDiagonal(); break;
		case 13: basePicture.chromakey(); break;
		}
		setupPicture();
	}
	
	private void setupListeners()
	{
		filterBox.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent currentEvent)
			{
				changeEffect(filterBox.getSelectedIndex());
			}
			
		});
		fileBox.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent arg0)
			{
				setBasePicture(fileArray[fileBox.getSelectedIndex()]);
				changeEffect(filterBox.getSelectedIndex());
				setupPicture();
				
			}
			
		}
		
		);
	}
}
