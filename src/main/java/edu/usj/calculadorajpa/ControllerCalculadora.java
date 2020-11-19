package edu.usj.calculadorajpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControllerCalculadora {

    //List<String> historico = new ArrayList<>();

    @Autowired
    HistoricoDeOperacoesRepositorio historicoDeOperacoesRepositorio;

    @PostMapping(value="/calcular")
    public ModelAndView postCalcular(@RequestParam String a, String b, String operador) {

        List<HistoricoDeOperacoes> historico = historicoDeOperacoesRepositorio.findAll();

        ModelAndView modelAndView = new ModelAndView("index");
        
        String resultado = "";

        switch (operador) {
            case "+":
                resultado = somar(a, b);
                break;
            case "-":
                resultado = diminuir(a, b);
                break;
            case "*":
                resultado = multiplicar(a, b);
                break;
            case "/":
                resultado = dividir(a, b);
                break;  
            default:
                break;
        }
        
        //historico.add(resultado);
        HistoricoDeOperacoes item = new HistoricoDeOperacoes();
        item.setOperacao(resultado);
        historicoDeOperacoesRepositorio.save(item);

        modelAndView.addObject("resultado", resultado);

        modelAndView.addObject("historico", historico);


        return modelAndView;
    }

    //FUNÇÕES

    public String somar(String a, String b) {
        Double aDouble = Double.valueOf(a);
        Double bDouble = Double.valueOf(b);
        Double resultado = aDouble + bDouble;
        String operacao = String.format("%s + %s = %s", a, b, resultado);
        return operacao;
    }

    public String diminuir(String a, String b) {
        Double aDouble = Double.valueOf(a);
        Double bDouble = Double.valueOf(b);
        Double resultado = aDouble - bDouble;
        String operacao = String.format("%s - %s = %s", a, b, resultado);
        return operacao;
    }

    public String multiplicar(String a, String b) {
        Double aDouble = Double.valueOf(a);
        Double bDouble = Double.valueOf(b);
        Double resultado = aDouble * bDouble;
        String operacao = String.format("%s * %s = %s", a, b, resultado);
        return operacao;
    }

    public String dividir(String a, String b) {
        Double aDouble = Double.valueOf(a);
        Double bDouble = Double.valueOf(b);
        Double resultado = aDouble / bDouble;
        String operacao = String.format("%s / %s = %s", a, b, resultado);
        return operacao;
    }
    
    
}
