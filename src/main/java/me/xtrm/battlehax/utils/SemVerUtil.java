package me.xtrm.battlehax.utils;

/**
 * Util used for SemVer & Version Check... probs useless
 * @author xTrM_
 */
public class SemVerUtil {
	
	public static int[] parseVersion(String version) {
		try {
			String[] splits = version.split(".");
			int[] ver = new int[splits.length];
			for(int i = 0; i < splits.length; i++)
				ver[i] = Integer.parseInt(splits[i]);
			return ver;
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return new int[0];
		}
	}
	
	public static String parseVersion(int[] version) {
		if(version.length == 0)
			return "";
		
		StringBuilder sb = new StringBuilder();
		for(int i : version)
			sb.append(i + ".");
		String ver = sb.toString();
		return ver.substring(0, ver.length() - 1);
		
	}
	
	public static int getMajor(int[] version) { return version[0]; }
	public static int getMinor(int[] version) { return version[1]; }
	public static int getPatch(int[] version) { return version[2]; }

}
