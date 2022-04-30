package carsharing.repository;

import carsharing.model.Company;
import carsharing.model.dao.CompanyDao;
import carsharing.model.dto.CompanyDto;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarSharingRepository {

    private Connection databaseConnection;

    public CarSharingRepository(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<CompanyDto> getAllCompanies() {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        List<Company> companiesList = companyDao.getAll();

        List<CompanyDto> resultList = companiesList.stream()
                .map(company -> new CompanyDto(company.getId(), company.getName()))
                .sorted(Comparator.comparing(companyDto -> companyDto.getId()))
                .collect(Collectors.toList());

        return resultList;
    }

    public int insertNewCompany(CompanyDto companyDto) {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        Company company = new Company(companyDto.getName());

        companyDao.save(company);

        return 0;
    }
}
