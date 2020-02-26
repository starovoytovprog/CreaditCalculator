package ru.starovoytov;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

/**
 * Генератор страниц из шаблона
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class PageGenerator {
	private static String TEMP_DIR = System.getProperty("user.home") + File.separator + ".stepicJavaWebServices1Temp" + File.separator;
	private static final PageGenerator PAGE_GENERATOR = new PageGenerator();
	private final Configuration cfg;

	/**
	 * private-конструктор
	 */
	private PageGenerator() {
		cfg = new Configuration();
		cfg.setTemplateLoader(new TempFileTemplateLoader());
	}

	/**
	 * Получить инстанс генератора
	 *
	 * @return
	 */
	public static PageGenerator instance() {
		return PAGE_GENERATOR;
	}

	/**
	 * Получение страницы из шаблона
	 *
	 * @param filename путь к шаблону
	 * @param data     параметры
	 * @return сформированная страница
	 */
	public String getPage(String filename, Map<String, Object> data) {
		Writer stream = new StringWriter();
		try {
			Template template = cfg.getTemplate(filename);
			template.process(data, stream);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return stream.toString();
	}

	/**
	 * Реализация загрузчика шаблонов
	 */
	private class TempFileTemplateLoader implements TemplateLoader {
		@Override
		public Object findTemplateSource(String name) throws IOException {
			String sourceName = name;
			InputStream is = PageGenerator.class.getClassLoader()
				.getResourceAsStream(sourceName);
			if (is != null) {
				File tempFile = new File(TEMP_DIR + sourceName);

				File destFolder = tempFile.getParentFile();
				if (!destFolder.exists()) {
					destFolder.mkdirs();
				}

				Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				return tempFile;
			}

			return null;
		}

		@Override
		public long getLastModified(Object templateSource) {
			return ((File) templateSource).lastModified();
		}

		@Override
		public Reader getReader(Object templateSource,
			String encoding) throws IOException {
			return new FileReader((File) templateSource);
		}

		@Override
		public void closeTemplateSource(Object templateSource) throws IOException {
			((File) templateSource).delete();
		}
	}
}
