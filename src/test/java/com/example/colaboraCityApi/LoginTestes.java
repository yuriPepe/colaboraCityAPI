import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import com.example.colaboraCityApi.controllers.LoginController;

public class LoginTestes {
    // Teste de login
@Test
public void testeLoginValido() {

    private LoginController controller;

  String login = "joao@email.com";
  String senha = "123456";

  Assert.assertTrue(controller.login(login, senha));  
}


@Test
public void testeLoginInvalido() {
  String login = "nao_cadastrado";
  String senha = "123";
  
  Assert.assertFalse(controller.login(login, senha));
}


}
