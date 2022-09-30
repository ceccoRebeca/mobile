class Eletronicos(nome: String, precoCompra: Float, precoVenda: Float, codigo: String,quantidade: Int,
                  var tipo: String, var versao: Float, var anoFabricacao: Int) :
    Produto(nome, precoCompra, precoVenda, codigo, quantidade) {

    override fun toString(): String {
        return "E-" + super.toString()
    }
}

