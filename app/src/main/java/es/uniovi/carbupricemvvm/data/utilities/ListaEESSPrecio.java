
package es.uniovi.carbupricemvvm.data.utilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaEESSPrecio{

    @SerializedName("C.P.")
    @Expose
    private String cP;
    @SerializedName("Direcci\u00f3n")
    @Expose
    private String direcciN;
    @SerializedName("Horario")
    @Expose
    private String horario;
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Localidad")
    @Expose
    private String localidad;
    @SerializedName("Longitud (WGS84)")
    @Expose
    private String longitudWGS84;
    @SerializedName("Margen")
    @Expose
    private String margen;
    @SerializedName("Municipio")
    @Expose
    private String municipio;
    @SerializedName("Precio Biodiesel")
    @Expose
    private String precioBiodiesel;
    @SerializedName("Precio Bioetanol")
    @Expose
    private String precioBioetanol;
    @SerializedName("Precio Gas Natural Comprimido")
    @Expose
    private String precioGasNaturalComprimido;
    @SerializedName("Precio Gas Natural Licuado")
    @Expose
    private String precioGasNaturalLicuado;
    @SerializedName("Precio Gases licuados del petr\u00f3leo")
    @Expose
    private String precioGasesLicuadosDelPetrLeo;
    @SerializedName("Precio Gasoleo A")
    @Expose
    private String precioGasoleoA;
    @SerializedName("Precio Gasoleo B")
    @Expose
    private String precioGasoleoB;
    @SerializedName("Precio Gasoleo Premium")
    @Expose
    private String precioGasoleoPremium;
    @SerializedName("Precio Gasolina 95 E10")
    @Expose
    private String precioGasolina95E10;
    @SerializedName("Precio Gasolina 95 E5")
    @Expose
    private String PrecioGasolina95E5;
    @SerializedName("Precio Gasolina 95 E5 Premium")
    @Expose
    private String PrecioGasolina95E5Premium;
    @SerializedName("Precio Gasolina 98 E10")
    @Expose
    private String PrecioGasolina98E10;
    @SerializedName("Precio Gasolina 98 E5")
    @Expose
    private String PrecioGasolina98E5;
    @SerializedName("Precio Hidrogeno")
    @Expose
    private String PrecioHidrogeno;
    @SerializedName("Provincia")
    @Expose
    private String provincia;
    @SerializedName("Remisi\u00f3n")
    @Expose
    private String remisiN;
    @SerializedName("R\u00f3tulo")
    @Expose
    private String rTulo;
    @SerializedName("Tipo Venta")
    @Expose
    private String tipoVenta;
    @SerializedName("% BioEtanol")
    @Expose
    private String bioEtanol;
    @SerializedName("% \u00c9ster met\u00edlico")
    @Expose
    private String sterMetLico;
    @SerializedName("IDEESS")
    @Expose
    private String iDEESS;
    @SerializedName("IDMunicipio")
    @Expose
    private String iDMunicipio;
    @SerializedName("IDProvincia")
    @Expose
    private String iDProvincia;
    @SerializedName("IDCCAA")
    @Expose
    private String iDCCAA;

    public String getCP() {
        return cP;
    }

    public void setCP(String cP) {
        this.cP = cP;
    }

    public String getDirecciN() {
        return direcciN;
    }

    public void setDirecciN(String direcciN) {
        this.direcciN = direcciN;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLongitudWGS84() {
        return longitudWGS84;
    }

    public void setLongitudWGS84(String longitudWGS84) {
        this.longitudWGS84 = longitudWGS84;
    }

    public String getMargen() {
        return margen;
    }

    public void setMargen(String margen) {
        this.margen = margen;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(String precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public String getPrecioBioetanol() {
        return precioBioetanol;
    }

    public void setPrecioBioetanol(String precioBioetanol) {
        this.precioBioetanol = precioBioetanol;
    }

    public String getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public void setPrecioGasNaturalComprimido(String precioGasNaturalComprimido) {
        this.precioGasNaturalComprimido = precioGasNaturalComprimido;
    }

    public String getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public void setPrecioGasNaturalLicuado(String precioGasNaturalLicuado) {
        this.precioGasNaturalLicuado = precioGasNaturalLicuado;
    }

    public String getPrecioGasesLicuadosDelPetrLeo() {
        return precioGasesLicuadosDelPetrLeo;
    }

    public void setPrecioGasesLicuadosDelPetrLeo(String precioGasesLicuadosDelPetrLeo) {
        this.precioGasesLicuadosDelPetrLeo = precioGasesLicuadosDelPetrLeo;
    }

    public String getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public void setPrecioGasoleoA(String precioGasoleoA) {
        this.precioGasoleoA = precioGasoleoA;
    }

    public String getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public void setPrecioGasoleoB(String precioGasoleoB) {
        this.precioGasoleoB = precioGasoleoB;
    }

    public String getPrecioGasoleoPremium() {
        return precioGasoleoPremium;
    }

    public void setPrecioGasoleoPremium(String precioGasoleoPremium) {
        this.precioGasoleoPremium = precioGasoleoPremium;
    }

    public String getPrecioGasolina95E10() {
        return precioGasolina95E10;
    }

    public void setPrecioGasolina95E10(String precioGasolina95E10) {
        this.precioGasolina95E10 = precioGasolina95E10;
    }

    public String getPrecioGasolina95E5() {
        return PrecioGasolina95E5;
    }

    public void setPrecioGasolina95E5(String precioGasolina95E5) {
        PrecioGasolina95E5 = precioGasolina95E5;
    }

    public String getPrecioGasolina95E5Premium() {
        return PrecioGasolina95E5Premium;
    }

    public void setPrecioGasolina95E5Premium(String precioGasolina95E5Premium) {
        PrecioGasolina95E5Premium = precioGasolina95E5Premium;
    }

    public String getPrecioGasolina98E10() {
        return PrecioGasolina98E10;
    }

    public void setPrecioGasolina98E10(String precioGasolina98E10) {
        PrecioGasolina98E10 = precioGasolina98E10;
    }

    public String getPrecioGasolina98E5() {
        return PrecioGasolina98E5;
    }

    public void setPrecioGasolina98E5(String precioGasolina98E5) {
        PrecioGasolina98E5 = precioGasolina98E5;
    }

    public String getPrecioHidrogeno() {
        return PrecioHidrogeno;
    }

    public void setPrecioHidrogeno(String precioHidrogeno) {
        PrecioHidrogeno = precioHidrogeno;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRemisiN() {
        return remisiN;
    }

    public void setRemisiN(String remisiN) {
        this.remisiN = remisiN;
    }

    public String getRTulo() {
        return rTulo;
    }

    public void setRTulo(String rTulo) {
        this.rTulo = rTulo;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getBioEtanol() {
        return bioEtanol;
    }

    public void setBioEtanol(String bioEtanol) {
        this.bioEtanol = bioEtanol;
    }

    public String getSterMetLico() {
        return sterMetLico;
    }

    public void setSterMetLico(String sterMetLico) {
        this.sterMetLico = sterMetLico;
    }

    public String getIDEESS() {
        return iDEESS;
    }

    public void setIDEESS(String iDEESS) {
        this.iDEESS = iDEESS;
    }

    public String getiDMunicipio() {
        return iDMunicipio;
    }

    public void setiDMunicipio(String iDMunicipio) {
        this.iDMunicipio = iDMunicipio;
    }

    public String getiDProvincia() {
        return iDProvincia;
    }

    public void setiDProvincia(String iDProvincia) {
        this.iDProvincia = iDProvincia;
    }

    public String getiDCCAA() {
        return iDCCAA;
    }

    public void setiDCCAA(String iDCCAA) {
        this.iDCCAA = iDCCAA;
    }
}
