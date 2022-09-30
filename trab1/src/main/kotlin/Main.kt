import java.io.InputStream


fun fazerCompras(nomeArquivo: InputStream): MutableMap<Produto,Int>{
    val leitor = nomeArquivo.bufferedReader()
    val cabecalho = leitor.readLine()

    return leitor.lineSequence()
        .filter { it.isNotBlank() }
        .map{
            val (codigo,qtd,nome,precoCom,precoVen,categoria)=
                it.split(',', ignoreCase = False, limit = 14)
            Produto
        }.toMutableSet(Produto,String)
}

fun fazerVendas(nome : String){

}

fun gerenciarEstoque(){

}

fun gerarBalancete(){

}

fun realizarBusca(filtro: Filtro, nomeArquivo: InputStream){

    if (filtro.categoria == null){

    }
    else {
        if (filtro.categoria is Roupa) {

        } else if (filtro.categoria is Eletronicos) {

        } else if (filtro.categoria is Colecionaveis) {

        }
    }
}

fun parserArquivos(nomeArquivo: InputStream){
    if(nomeArquivo.equals("busca")){
        //criar filtro
        var filtro = Filtro(null,null,null,null,null,null,null,null,null)
        realizarBusca(filtro)
    }
    else if(nomeArquivo.equals("compras")){

    }
    else if(nomeArquivo.equals("vendas")){

    }
}
//Elvis dessa galera toda aqui!!!!
data class Filtro(
    var categoria: Produto? = null,
    var tipo: String? = null,
    var tamanhoRoupa: tamanhoRoupa? = null,
    var corPrimaria: String? = null,
    var corSecundaria: String? = null,
    var versao: Float? = null,
    var anoFabricacao: Int? = null,
    var material: String?= null,
    var relevancia: Relevancia? = null)


fun main(args: Array<String>) {


    var r1 = Roupa("camisa",29.5F,50F, "EL16NH",10,
        tipoRoupa.camisa, tamanhoRoupa.G,"Preta","Azul")
    var e1 = Eletronicos("fone", 50F,90F, "FN01AKG",8,
        "audio",2F,2021)
    var c1 = Colecionaveis("funkoKM",100F,180F,"FK09880KM",5,
        "Boneco","Misturado",8F, Relevancia.M)

    var estoque =  mutableMapOf<Produto,String>(Pair(r1,r1.codigo), Pair(e1,e1.codigo))



    var f1 = Filtro(r1,null, null, "vermelho", null,
        null,null,null,null )

}