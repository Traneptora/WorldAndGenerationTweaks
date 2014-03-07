package thebombzen.mods.worldandgenerationtweaks.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


import net.minecraft.launchwrapper.IClassTransformer;

public class ASMClassTransformer implements IClassTransformer {

	private JarFile wagtJarfile;
	
	public ASMClassTransformer() {
		URLClassLoader urlcl = (URLClassLoader) WAGTCorePlugin.class
				.getClassLoader();
		URL[] urls = urlcl.getURLs();
		for (URL url : urls) {
			try {
				JarFile jarFile = new JarFile(new File(url.toURI()));
				if (jarFile
						.getEntry("thebombzen/mods/worldandgenerationtweaks/loader/ASMClassTransformer.class") == null) {
					jarFile.close();
				} else {
					wagtJarfile = jarFile;
				}
			} catch (Exception e) {
			}
		}
	}
	
	private byte[] getWAGTClass(String name) {
		if (wagtJarfile == null) {
			return null;
		}
		String fullName = name + ".class";
		JarEntry je = wagtJarfile.getJarEntry(fullName);
		if (je == null)
			return null;
		try {
			InputStream in = wagtJarfile.getInputStream(je);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte[] buf = new byte[8192];
			int n;
			while (-1 != (n = in.read(buf))) {
				baos.write(buf, 0, n);
			}
			baos.close();
			byte[] bytes = baos.toByteArray();
			if (bytes.length != je.getSize()) {
				System.out.println("Invalid size for " + fullName + ": "
						+ bytes.length + ", should be: " + je.getSize());
				return null;
			}
			return bytes;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] transform(String name, String newName, byte[] bytes) {
		if (name.length() > 3)
			return bytes;
		if (!name.toLowerCase().equals(name)) {
			return bytes;
		}
		byte[] newBytes = getWAGTClass(name);
		return newBytes != null ? newBytes : bytes;
	}
}
