package cz.muni.fi.pv243.dao;

import java.util.List;
import cz.muni.fi.pv243.model.SellingCompany;


/**
 * @author dubrouski
 *
 */
public interface SellingCompanyDao {

	/**
     * Stores company into database. Id for the new company is automatically
     * generated and stored into id attribute.
     *
     * @param company company to be created.
     *
     */
    public void createSellingCompany(SellingCompany company);
    
    /**
     * Updates given company (matching targeted company by id)
     *
     * @param company company to be updated.
     *
     */
    public void updateSellingCompany(SellingCompany company);    
    
    /**
     * Removes given company from database.
     *
     * @param company company to be deleted.
     *
     */
    public void deleteSellingCompany(SellingCompany company);
    
    /**
     * Returns all companies.
     *
     */
    public List<SellingCompany> getAllSellingCompanies();
    
    /**
     * Returns company by given id.
     *
     * @param id id of company to be returned.
     *
     */
    public SellingCompany getSellingCompanyById(Long id);
    
}
