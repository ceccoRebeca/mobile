import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

var estoque: MutableMap<String,Produto> = mutableMapOf<String,Produto>()
var historicoVendas = mutableListOf<Historico>()
var historicoCompras = mutableListOf<Historico>()
var totalVendido: Float = 0f
var totalComprado: Float = 0f


fun realizarCompras(nomeArquivo: InputStream){

    var compras = parserLinhas(nomeArquivo)
    //tirando cabeçalho
    compras = compras.drop(1)


    compras.forEach {
        historicoCompras.add(Historico(it[0],it[1].toInt()))
        totalComprado += it[1].toInt()*it[3].toFloat()

        if(it[5].equals("eletronico") && it[6].equals("video-game")){
            val e = Eletronicos(it[0],it[1].toInt(),it[2], it[3].toFloat(),it[4].toFloat(),
                    tipoEletronico.videogame,it[10].toInt(),it[11].toInt())
            estoque[e.codigo] = e
        }
        else if(it[5].equals("eletronico")){
            val e = Eletronicos(it[0],it[1].toInt(),it[2], it[3].toFloat(),it[4].toFloat(),
                tipoEletronico.valueOf(it[6]),it[10].toInt(),it[11].toInt())
            estoque[e.codigo] = e
        }
        else if(it[5].equals("roupa")){
            val r = Roupa(it[0],it[1].toInt(),it[2], it[3].toFloat(),it[4].toFloat(),
                    tipoRoupa.valueOf(it[6]), tamanhoRoupa.valueOf(it[7]),it[8],it[9])
            estoque[r.codigo] = r
        }
        else if(it[5].equals("colecionavel") && it[6].equals("boneco")){
            val c  = Colecionaveis(it[0],it[1].toInt(),it[2], it[3].toFloat(),it[4].toFloat(),
                    tipoColecionavel.valueOf(it[6]),materialFabricacao.valueOf(it[12]),
                    it[7].toFloat(), Relevancia.valueOf(it[13]))
            estoque[c.codigo] = c
        }
        else if(it[5].equals("colecionavel")){
            val c  = Colecionaveis(it[0],it[1].toInt(),it[2], it[3].toFloat(),it[4].toFloat(),
                tipoColecionavel.valueOf(it[6]),materialFabricacao.valueOf(it[12]),
                0f, Relevancia.valueOf(it[13]))
            estoque[c.codigo] = c
        }

    }
}

data class Historico(
    val codigo: String,
    val qtd: Int
)

fun realizarVendas(nomeArquivo: InputStream){

    var listagemVendas = parserLinhas(nomeArquivo)
    listagemVendas = listagemVendas.drop(1)
    var isVenda: Boolean

    listagemVendas.forEach {
        isVenda = realizarVenda(it[0].drop(2),it[1].toInt())
        if (isVenda){
            historicoVendas.add(Historico(it[0],it[1].toInt()))
            totalVendido += it[1].toInt()*estoque[it[0].drop(2)]!!.precoVenda
        }
    }
}

fun realizarVenda(codigo: String, qtd: Int): Boolean{

    if (estoque[codigo]!= null){
        if(estoque[codigo]!!.quantidade >= qtd) {
            estoque[codigo]!!.quantidade -= qtd
            return true
        }
        else {
            println("Quantidade de produto: $codigo insuficiente para realizar venda")
            return false
        }
    }
    else {
        println("Produto $codigo não encontrado")
        return false
    }
}

fun OutputStream.gerarRelatorioGeral(estoque: Map<String, Produto>){

    val escritor = bufferedWriter()
    escritor.write("CODIGO,NOME,QUANTIDADE")
    escritor.newLine()

    estoque.forEach{
        escritor.write("${it.value}, ${it.value.nome.uppercase()},${it.value.quantidade}")
        escritor.newLine()
    }
    escritor.flush()

}
fun OutputStream.gerarRelatorioCategoria(estoque: Map<String, Produto>){
    var qtdRoupa = 0
    var qtdColecionavel = 0
    var qtdEletronico  = 0

    val escritor = bufferedWriter()
    escritor.write("CATEGORIA,QUANTIDADE")
    escritor.newLine()

    for (key in estoque.keys) {
        if (estoque[key] is Roupa){
            qtdRoupa += estoque[key]!!.quantidade
        }
        else if(estoque[key] is Colecionaveis){
            qtdColecionavel += estoque[key]!!.quantidade
        }
        else
            qtdEletronico += estoque[key]!!.quantidade
    }
    escritor.write("ROUPA, $qtdRoupa")
    escritor.newLine()
    escritor.write("COLECIONAVEL, $qtdColecionavel")
    escritor.newLine()
    escritor.write("ELETRONICO, $qtdEletronico")
    escritor.newLine()

    escritor.flush()

}

fun OutputStream.gerarBalancete(estoque: Map<String, Produto>){
    val escritor = bufferedWriter()
    escritor.write("COMPRAS,$totalComprado")
    escritor.newLine()
    escritor.write("VENDAS,$totalVendido")
    escritor.newLine()
    escritor.write("BALANCETE,${totalVendido-totalComprado}")

    escritor.flush()
}

fun parserLinhas(arquivo: InputStream): List<List<String>>{

    var linhas: MutableList<List<String>> = mutableListOf()

    arquivo.bufferedReader().forEachLine {
        linhas.add(it.split(","))
    }

    return linhas
}


fun main(args: Array<String>) {
    val caminhoEntrada = args[0]
    val caminhoSaida = args[1]

    val arquivoCompras = "$caminhoEntrada\\compras.csv"
    var inputStreamCompras: InputStream = File (arquivoCompras).inputStream()

    val arquivoVendas = "$caminhoEntrada\\vendas.csv"
    var inputStreamVendas: InputStream = File (arquivoVendas).inputStream()

    realizarCompras(inputStreamCompras)
    realizarVendas(inputStreamVendas)

    FileOutputStream(caminhoSaida+"estoque_geral.csv").apply { gerarRelatorioGeral(estoque) }
    FileOutputStream(caminhoSaida+"estoque_categorias.csv").apply { gerarRelatorioCategoria((estoque)) }
    FileOutputStream(caminhoSaida+"balancete.csv").apply { gerarBalancete(estoque) }


}