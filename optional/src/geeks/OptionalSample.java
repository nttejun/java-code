package geeks;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class OptionalSample {

  @Test
  public void getNullValueNomal() {
    String[] words = new String[10];
    String word = words[5].toLowerCase();
    System.out.println(word);
  }

  @Test
  public void checkNullValueInOptional() {
    String[] words = new String[10];
    Optional<String> checkNull = Optional.ofNullable(words[5]);
    Assert.assertFalse(checkNull.isPresent());
  }

  @Test
  public void checkEmptyValueInOptional(){
    String[] str = new String[5];
    str[2] = "Hello world !!";
    Optional<String> empty = Optional.empty();
    Assert.assertEquals(Optional.empty(), empty);
    Assert.assertNotEquals("Optional.empty", empty);
  }

  @Test
  public void checkOptionalOfValue(){
    String[] str = new String[5];
    str[2] = "Hello world !!";
    Optional<String> value = Optional.of(str[2]);
    Assert.assertEquals("Optional[Hello world !!]", value.toString());
    Assert.assertNotEquals("Optional[Hello world !!]", value.get());
    Assert.assertEquals("Hello world !!", value.get());
  }
}
