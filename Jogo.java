import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Jogo {
    public static void main(String[] args) throws Exception{
        Personagem p1 = new Cacador("Jarbas");         
        Personagem p2 = new Bardo("Tico Trovador", 3, 8, 8);
        var gerador = new Random();
        //boolean campeaoDeclarado = false;
        while(true){
            p1.realizarAcaoAleatoria();
            p2.realizarAcaoAleatoria();
            var horaDoDuelo = gerador.nextInt(1, 3);
            if(!p1.estaMorto() && !p2.estaMorto()){
                
                switch(horaDoDuelo){
                    case 1:
                        p1.duelar(p2);
                        break;
                    case 2:
                        p2.duelar(p1);
                        break;
                }
            }       
                        
            System.out.println(p1);
            System.out.println(p2);
            System.out.println("******************************");
            if(p1.estaMorto() && p2.estaMorto()){
                System.out.println("Fim de Jogo!");
                break;
            }
            Thread.sleep(5000);
        }
        
    }
}