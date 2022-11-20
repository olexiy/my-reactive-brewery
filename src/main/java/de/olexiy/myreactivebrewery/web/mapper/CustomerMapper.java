package de.olexiy.myreactivebrewery.web.mapper;

import de.olexiy.myreactivebrewery.domain.Customer;
import de.olexiy.myreactivebrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
  Customer customerDtoToCustomer(CustomerDto dto);
  CustomerDto customerToCustomerDto(Customer customer);
}
