package com.fdm.stockmodulation.project.stockmodulation.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Company")
@Table(
        name = "company"
//        uniqueConstraints = {
//                @UniqueConstraint(name="company_name_unique", columnNames = "name")
//        }
)
public class Company  {

    @Id
    @Column(
            name = "company_id",
            nullable = false,
            unique = true,
            columnDefinition = "bigint"
    )
    private Long companyId;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "text"
    )
    private String name;

    @Column(
            name = "description",
            columnDefinition = "text"
    )
    private String description;

    @Column(
            name = "sector",
            columnDefinition = "text"
    )
    private String sector;

    @Column(
            name = "ceo",
            columnDefinition = "text"
    )
    private String ceo;

    @Column(
            name = "address",
            columnDefinition = "text"
    )
    private String address;

    @Column(
            name = "valuation",
            columnDefinition = "bigint"
    )
    private Long valuation;

    @OneToOne(
//            mappedBy = "stock",
            cascade=CascadeType.REMOVE
    )
    @JoinColumn(
            name = "stock_id"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stock stock;

    public Company(String name, String description, String sector, String ceo, String address, Long valuation, Stock stock) {
        this.name = name;
        this.description = description;
        this.sector = sector;
        this.ceo = ceo;
        this.address = address;
        this.valuation = valuation;
        this.stock = stock;
    }

//    public Company(Long companyId, String name, String description, String sector, String ceo, String address, Long valuation, Stock stock) {
//        this.companyId = companyId;
//        this.name = name;
//        this.description = description;
//        this.sector = sector;
//        this.ceo = ceo;
//        this.address = address;
//        this.valuation = valuation;
//        this.stock = stock;
//    }

    public Company() { }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getValuation() {
        return valuation;
    }

    public void setValuation(Long valuation) {
        this.valuation = valuation;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sector='" + sector + '\'' +
                ", ceo='" + ceo + '\'' +
                ", address='" + address + '\'' +
                ", valuation=" + valuation + '\'' +
                ", stock=" + stock.toString() +
                '}';
    }
}
