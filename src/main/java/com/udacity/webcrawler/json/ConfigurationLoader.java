package com.udacity.webcrawler.json;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader  {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load()  {
    //load() method will read the JSON string from a file Path which has already been provided
    // to the ConfigurationLoader constructor.
    // Pass that string to the read(Reader reader) and return the created CrawlerConfiguration
      try(Reader reader = Files.newBufferedReader(path);){
          return read(reader);
      }catch (IOException e) {
          e.printStackTrace();
          return null;
      }
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  static CrawlerConfiguration read(Reader reader) throws IOException {
   Objects.requireNonNull(reader);
   ObjectMapper objectMapper=new ObjectMapper();
   objectMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
   return objectMapper.readValue(reader, CrawlerConfiguration.Builder.class).build();
  }

}
