package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 该类用来加载和组织图片
 * @author duoduo
 *
 */
public class ImageLoader
{
	// image抽象类的实体类，将图片加载到内存中
	private BufferedImage image;
	
	public ImageLoader()
	{}
	
	/**
	 * 此方法尝试加载图像，并在 filme in 参数上读取
	 * @param name
	 */
	public void loadImage(String name)
	{
		try
		{
			//工程目录路径
			String separator = System.getProperty("file.separator");
			//javax.imageio.ImageIO读取标准格式的图片
			image = ImageIO.read(new File("src" + separator + "Assets" + separator + name));
		}
		catch (IOException ex)
		{
		}		
	}
	
	/**
	 * @return
	 */
	public BufferedImage getImage()
	{
		return this.image;
	}
}