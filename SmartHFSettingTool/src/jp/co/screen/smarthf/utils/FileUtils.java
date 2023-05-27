/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * 本プログラムの著作権は大日本スクリーン製造株式会社に帰属するものであり、
 * 同社はこれを営業秘密として管理するものです。従い、本プログラムの全て、
 * 一部にかかわらず、その複製、頒布を行うことは、同社の事前の書面による
 * 承諾がない限り固く禁じられるものです。
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.Properties;

import jp.co.screen.egleg.flc.bar.FlcConfigMap;
import jp.co.screen.egleg.util.fstr.WorkFolderStructure;
import jp.co.screen.smarthf.common.Constants;

import org.apache.log4j.Logger;

/**
 * The Class FileUtil.
 * 
 * @author trungtb
 */
public class FileUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(FileUtils.class);

	private static Properties properties;
	
	private static String USER_DIR = "user.dir";

	/**
	 * Copy.
	 * 
	 * @param srcFile
	 *            the src file
	 * @param destFile
	 *            the dest file
	 * @throws IOException
	 *             the exception
	 */
	public static void copy(File srcFile, File destFile) throws IOException {
		LOGGER.debug("Start FileUtil.copy(), srcFile=[" + srcFile + "]" + ", destFile=[" + destFile
				+ "]");
		if (!srcFile.exists()) {
			throw new IOException("copy: no such source file: " + srcFile.getAbsolutePath());
		}
		if (!srcFile.isFile()) {
			throw new IOException("copy: can't copy directory: " + srcFile.getAbsolutePath());
		}
		if (!srcFile.canRead()) {
			throw new IOException("copy: source file is unreadable: " + srcFile.getAbsolutePath());
		}

		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(srcFile);
			output = new FileOutputStream(destFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead); // write
			}
		} catch (IOException e) {
			LOGGER.error("Fail to copy file " + srcFile.getName() + " to "
					+ destFile.getAbsolutePath());
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error("Fail to copy file " + srcFile.getName() + " to "
							+ destFile.getAbsolutePath());
					LOGGER.error(e.getMessage(), e);
					throw e;
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					LOGGER.error("Fail to copy file " + srcFile.getName() + " to "
							+ destFile.getAbsolutePath());
					LOGGER.error(e.getMessage(), e);
					throw e;
				}
			}
		}
		LOGGER.debug("End FileUtil.copy()");
	}

	/**
	 * makeDirectory if not exist.
	 */
	public static void makeDirectory(String directoryPath) {
		LOGGER.debug("Start FileUtil.makeDirectory()");
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/**
	 * get Absolute path of specified file
	 */
	public static String getAbsolutePath(String filePath) {
		File file = new File(filePath);
		return file.getAbsolutePath();
	}

	public static boolean isExist(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists())
			return true;
		else
			return false;
	}
	
	public static boolean isExistFolder(String inFolderPath){
		File file = new File(inFolderPath);
		if (!file.isFile() && file.exists())
			return true;
		else
			return false;
	}

	/**
	 * 
	 * copyFileUsingIPCandCMD
	 * 
	 * @param ipAddress
	 * @param userName
	 * @param password
	 * @param srcFolder
	 * @param shareFolder
	 * @throws IOException
	 */
	public static void copyFileUsingIPCandCMD(String ipAddress, String userName, String password,
			String srcFolder, String shareFolder) throws IOException {

		try {

			String commandConnect = "cmd /c net use \\\\" + ipAddress + "\\ipc$ /user:" + userName
					+ " " + password;

			Runtime.getRuntime().exec(commandConnect);
			String commandCopy = "cmd /c COPY /Y " + srcFolder + " " + shareFolder;

			Runtime.getRuntime().exec(commandCopy);
		} finally {

			String commandDisconnect = "cmd /c net use \\\\" + ipAddress + "\\ipc$ /d";

			Runtime.getRuntime().exec(commandDisconnect);

		}

	}

	/**
	 * 
	 * write text file
	 * 
	 * @param data
	 * @param workdir
	 * @param fileName
	 * @return boolean : write result
	 */
	public static boolean writeFile(String data, String workdir, String fileName) {
		boolean result = true;
		// ファイル生成
		BufferedOutputStream bufOut = null;
		File serverFile = null;
		try {
			File work = new File(workdir);
			work.mkdirs();
			serverFile = new File(workdir + Constants.DOUBLE_BACK_SLASH + fileName);
			if (serverFile.exists()) {
				serverFile.delete();
			}

			/*
			 * in case there is no any server (all servers are deleted)
			 */
			if (data.equals("")) {
				return true;
			}
			serverFile.createNewFile();
			bufOut = new BufferedOutputStream(new FileOutputStream(serverFile));
			byte[] writeByte = data.getBytes();
			int len = writeByte.length;
			for (int i = 0; i < len; i++) {
				bufOut.write(writeByte[i]);
			}
			bufOut.flush();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			try {
				if (bufOut != null)
					bufOut.close();
			} catch (IOException ie) {
			} finally {
				bufOut = null;
			}
		}
		return result;
	}

	/**
	 * 
	 * delete file
	 * 
	 * @param inFile
	 * @return true: delete successful. Otherwise: false
	 */
	public static final synchronized boolean deleteFile(File inFile) {
		if (inFile == null)
			return false;
		if (!inFile.exists())
			return true;

		boolean isDelete = false;
		try {
			inFile.delete();
			// 削除されたかどうか確認する
			isDelete = !inFile.exists();
		} catch (Exception e) {
		}

		return isDelete;
	}

	/**
	 * Load application config from file.
	 * 
	 * @throws IOException
	 *             exception when reading file
	 */

	public static void loadApplicationConfig() throws IOException {
		FileInputStream fis = null;
		 String appConfigPath = Constants.INI_CONFIG_FILE;
		try {
			LOGGER.error("appConfigPath:" + appConfigPath);
			fis = new FileInputStream(appConfigPath);
			properties.load(fis);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
	
	/**
	 * Get properties 
	 * @return properties
	 * @since EQUIOS V2.02 EQ203 EQF#C492
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Read the config content.
	 * 
	 * @param key
	 *            Specific config key
	 * @return The content of the config key
	 */
	public static String readConfigContent(String key) {
		// get path of app
		String appPath = getApplicationPath();
		// Define variable to hold the content of config section
		String configContent = Constants.EMPTY_STRING;
		try {

			// Current life
			String thisLine;
			// Create the input stream to read file
			InputStream ist = new FileInputStream(Constants.INI_CONFIG_FILE);
			// Create the buffer reader to for Input Stream
			BufferedReader istream = new BufferedReader(new InputStreamReader(ist,
					Constants.UTF8_ENCODING));

			// read all line to find the specified config section
			while ((thisLine = istream.readLine()) != null) {
				// Check this line is match the config section
				if (thisLine.startsWith(key)) {
					// get the index of "=" value
					int equalIndex = thisLine.indexOf(Constants.EQUAL);

					if (equalIndex > 0) {
						// get the content from the index of "=" character to
						// end of line
						configContent = thisLine.substring(equalIndex + 1, thisLine.length());

						// Break the loop when found the content
						break;
					}

				}
			}
			// Close the stream
			if (istream != null) {
				istream.close();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return configContent;
	}

	/**
	 * Write the config content to file.
	 * 
	 * @param key
	 *            specific key
	 * @param value
	 *            specific value
	 */
	static void writeConfigContent(String key, String value) {
		// get path of app
		String appPath = getApplicationPath();
		// build path to app config file
		String appConfigPath = Constants.INI_CONFIG_FILE;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter out = null;

		try {

			String strBuffer = Constants.EMPTY_STRING;
			String thisLine;
			InputStream ist = new FileInputStream(appConfigPath);
			BufferedReader istream = new BufferedReader(new InputStreamReader(ist,
					Constants.UTF8_ENCODING));
			boolean add = false;
			while ((thisLine = istream.readLine()) != null) {
				if (thisLine.startsWith(key)) {
					strBuffer += key + Constants.EQUAL + value;
					add = true;
				} else {
					strBuffer += thisLine;
				}
				strBuffer += Constants.CRLF;
			}
			if(!add){
				strBuffer += key + Constants.EQUAL + value;
			}
			if (istream != null) {
				istream.close();
			}
			// write file and replace SHA256_STAGING_PL by hash key
			fos = new FileOutputStream(appConfigPath);

			// osw = new OutputStreamWriter(fos, "ISO-8859-1");
			// osw = new OutputStreamWriter(fos, "ISO-2022-JP");
			osw = new OutputStreamWriter(fos, Constants.UTF8_ENCODING);
			// osw = new OutputStreamWriter(fos, "SJIS");
			out = new BufferedWriter(osw);

			out.write(strBuffer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (osw != null) {
					osw.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * Get the application path .
	 * 
	 * @return application path
	 * @since EQUIOS V2.02 EQ203 EQF#C492
	 */
	private static String getApplicationPath() {
		String appPath = "";
		String isDebug = System.getProperty("debug");
		if (isDebug != null && isDebug.equals("true")) {
			appPath = System.getProperty(USER_DIR);
		} else {
			// appPath = System.getProperty(USER_DIR) + File.separator + "./..";
			appPath = System.getProperty(USER_DIR) + File.separator;
		}

		return appPath;
	}
	
	static String buildPath(final String path, String... subPaths) {
        StringBuilder fullPath = new StringBuilder(path);
        if (subPaths != null) {
            for (int i = 0; i < subPaths.length; i++) {
                boolean bEndBackSlash = false;
                boolean bStartBackSlash = false;
                if (fullPath.toString().endsWith(Constants.SLASH)
                        || fullPath.toString().endsWith(Constants.DOUBLE_BACK_SLASH)) {
                    bEndBackSlash = true;
                }
                if (subPaths[i] != null && subPaths[i].trim().length() > 0) {
                    if (subPaths[i].charAt(0) == '\\' || subPaths[i].charAt(0) == '/') {
                        bStartBackSlash = true;
                    }
                    if (bEndBackSlash || bStartBackSlash) {
                        fullPath.append(subPaths[i]);
                    } else {
                        fullPath.append(File.separator).append(subPaths[i]);
                    }
                }
            }
        }
        return fullPath.toString();
    }

	public static String getSmartHotfolderRootPath() {
		return Paths.get(FlcConfigMap.getWorkDir(), WorkFolderStructure.getSmartHotfolderPath()).toString();
	}

	public static String getRuleEngineMacroRootPath() {
		return Paths.get(FlcConfigMap.getWorkDir(), WorkFolderStructure.getRuleEngineMacroTxt()).toString();
	}
}