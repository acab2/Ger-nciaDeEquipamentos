package steps

import cucumber.api.PendingException


//import cucumber.steps.TestDataAndOperations
import gerenciadeequipamentos.Equipamento
import gerenciadeequipamentos.EquipamentoController
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def result

Given(~/^que o sistema possui o equipamento1$/) { ->
    def controlador = new EquipamentoController()
    def equipamento1 = new Equipamento("monitor", "funcionando", "grad2")
    def equipamento2 = new Equipamento("mouse", "funcionando", "grad2")
    controlador.params << [nome:equipamento1.getNome(), status:equipamento1.getStatus(), localizacao:equipamento1.getLocalizacao()]
    controlador.save()
    controlador.response.reset()
    controlador.params << [nome:equipamento2.getNome(), status:equipamento2.getStatus(), localizacao:equipamento2.getLocalizacao()]
    controlador.save()
    controlador.response.reset()
    assert Equipamento.findByNomeAndStatus("monitor", "funcionando")
    //TestDataAndOperations.createEquipamento(equipamento1, controlador)
    //TestDataAndOperations.createEquipamento(equipamento2, controlador)
}
When(~/^eu tento buscar equipamentos pelo seu status "([^"]*)" e localizacao "([^"]*)"$/) { String status, String localizacao ->
    def controlador = new EquipamentoController()
    result = controlador.buscaAvancada("", status, localizacao)

}

Then(~/^eu recebo uma lista com todos os equipamentos que possuem o Status e a Localizacao selecionada$/) { ->
    assert result
}

Given(~/^que estou na tela de busca avancada$/) {  ->
    def equipamento = new Equipamento("monitor", "funcionando", "grad2")

    to buscaAvancada
    at buscaAvancada
    page.busca(equipamento)
    at VerPage
    to CreatePage
    at CreatePage
    page.criarVaga(vaga2)
}
When(~/^seleciono os atributos nos campos de nome, status e localizacao$/) { ->

}
And(~/^solicito a busca$/) { ->

}
Then(~/^consigo ver a lista com o resultado da busca$/) { ->

}