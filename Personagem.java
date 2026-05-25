import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Personagem{
    String nome;
    private int energia;
    private int fome;
    private int sono;
    private ArrayList <String> mochila = new ArrayList<String>();
    private ArrayList <Musica> repertorio = new ArrayList<Musica>();

    ArrayList<Musica> colecao = new ArrayList<Musica>();

    public ArrayList<Musica> getRepertorio(){
        return repertorio;
    }
    
    public void gerarColecao(){
        colecao.add(new Musica("Time"));
        colecao.add(new Musica("Echoes"));
        colecao.add(new Musica("Into the Void"));
        colecao.add(new Musica("Solitude"));
        colecao.add(new Musica("Fairies Wear Boots"));
        colecao.add(new Musica("Take Five"));
        colecao.add(new Musica("Sonata No.27 op.90"));
        colecao.add(new Musica("Paranoid"));
        colecao.add(new Musica("Snowblind"));
        colecao.add(new Musica("Dogs"));
    }
    Personagem(){
        System.out.println("Construindo novo personagem");
        energia = 10;
        fome = 0;
        sono = 0;
    }
    Personagem(String nome){
        this();
        this.nome = nome;
    }
    Personagem(int energia, int fome, int sono) {
        System.out.println("Construindo novo personagem");
        this.energia = energia < 0 || energia > 10 ? 10 : energia;
        this.fome = fome >= 0 && fome <= 10 ? fome : 0;
        this.sono = sono >= 0 && sono <= 10 ? sono : 0;
    }
    Personagem(String nome,int energia, int fome, int sono){
        System.out.println("Construindo novo personagem");
        this.nome = nome;
        this.energia = energia < 0 || energia > 10 ? 10 : energia;
        this.fome = fome >= 0 && fome <= 10 ? fome : 0;
        this.sono = sono >= 0 && sono <= 10 ? sono : 0;
    }
    
    void cacar(){
        if(this.energia >= 2){
            System.out.printf("%s caçando\n", this.nome);
            this.energia -= 2; // energia = energia - 2
            loot();
        }else{
            System.out.printf("%s sem energia para caçar\n", this.nome);
        }
        // fome +=1 fome++ ++fome
        if (this.fome < 10) this.fome = this.fome + 1;
        // sono = sono + 1 > 10 ? sono : sono + 1;
        this.sono = this.sono == 10 ? this.sono : this.sono + 1;
    }
    //método comer
    void comer(){
        if(this.fome >= 1){
            System.out.println(this.nome + " comendo\n");
            this.energia = Math.min(this.energia + 1, 10);
            this.fome--;
        }
        else{
            System.out.println(this.nome + " sem fome\n");
        }
    }
    //método dormir
    void dormir(){
        if(this.sono >= 1){
        System.out.print(this.nome + " dormindo\n");
        this.energia = this.energia == 10 ? this.energia : this.energia + 1;
        this.sono -= 1;
        }else{
        System.out.println(this.nome + " sem sono\n");
        }
    }
    //método loot
    void loot(){
        var gerador = new Random();
        var gerarLoot = gerador.nextInt(1, 6);
        switch(gerarLoot){
            case 1:
                this.mochila.add("pena");
                break;
            case 2:
                this.mochila.add("couro");
                break;
            case 3:
                this.mochila.add("osso");
                break;
            case 4:
                this.mochila.add("presa");
                break;
            case 5:
                this.mochila.add("garra");
                break;
        }
    }

    abstract void realizarAcaoAleatoria();

    void novaMusica(Musica musica){
        this.repertorio.add(musica);
    }
    
    void aprenderMusica(){
        var gerador = new Random();
        var qualeAmusica = gerador.nextInt(0, this.colecao.size());
        if (this.repertorio.contains(this.colecao.get(qualeAmusica))){
            System.out.println(nome + " lembrou de uma musica\n");
        }  
        else{
            this.novaMusica(this.colecao.get(qualeAmusica));
            System.out.println("\n"+nome + " Aprendeu uma nova musica!\n");
        }
    }
    void dano(){
        this.energia -= 1;
    }
    boolean estaMorto(){
        if (this.energia <= 0){
            return true;
        }
        return false;
    }

    // void duelar(Personagem inimigo){
    //     if(repertorio.size() == 0){
    //         return;
    //     }
    //     else{
    //         var gerador = new Random();
    //         var qualeAmusica = gerador.nextInt(0, repertorio.size());
    //         Musica ataque = repertorio.get(qualeAmusica);
    //         if (inimigo.repertorio.contains(ataque)){
    //             this.dano();
    //             inimigo.dano();
    //             System.out.println("\nO publico ficou entediado.");
    //         }
    //         else{
    //             inimigo.dano();
    //             inimigo.novaMusica(ataque);
    //             System.out.println("\n"+inimigo.nome+" foi vencido pelo ineditismo de "+this.nome);
    //         }

    //     }
    // }

    public String toString(){
        var sb = new StringBuilder("");
        if(estaMorto()){
            sb.append("\n").append("Falecido");
        }
        sb.append("\n").append(this.nome).append(" status:");
        sb.append("\n").append("energia: ").append(this.energia);
        sb.append("\n").append("fome: ").append(this.fome);
        sb.append("\n").append("sono:").append(this.sono);
        sb.append("\n").append("repertorio:");
        for (int i = 0; i < this.repertorio.size(); i++){
            sb.append("\n").append(" - ").append(this.repertorio.get(i));
        }
        sb.append("\n").append("inventario:");
        for (int i = 0; i < this.mochila.size(); i++){
            sb.append("\n").append(" - ").append(this.mochila.get(i));
        }
        return sb.toString();
    }
}