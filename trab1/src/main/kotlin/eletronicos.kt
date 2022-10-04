class Eletronicos(
    codigo: String,
    quantidade: Int,
    nome: String,
    precoCompra: Float,
    precoVenda: Float,


    var tipo: tipoEletronico,
    var versao: Int,
    var anoFabricacao: Int): Produto(codigo, quantidade, nome, precoCompra, precoVenda) {

    override fun toString(): String {
        return "E-" + super.toString()
    }
}

