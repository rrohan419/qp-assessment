package com.rohan.grocery_booking.common.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.rohan.grocery_booking.common.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Mapper {

	private final ModelMapper modelMapper;

	/**
	 * Generic method to map source object to target class
	 * 
	 * @author rrohan419@gmail.com
	 * @param <T>
	 * @param srcObj
	 * @param targetClass
	 * @return T class object
	 */
	public <T> T convert(Object srcObj, Class<T> targetClass) {
		try {

			return modelMapper.map(srcObj, targetClass);

		} catch (IllegalArgumentException argumentException) {

			throw new CustomException("Source Destination is null");

		} catch (MappingException | ConfigurationException eRuntimeException) {

			throw new CustomException(eRuntimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Generic method to map source object list to list of target class
	 * 
	 * @author rrohan419@gmail.com
	 * @param <S>
	 * @param <T>
	 * @param srcList
	 * @param targetClass
	 * @return T class objects list
	 */
	public <S, T> List<T> convertToList(List<S> srcList, Class<T> targetClass) {
		List<T> response = new ArrayList<>();

		if (srcList != null) {
			srcList.stream().forEach(source -> response.add(convert(source, targetClass)));
		}

		return response;
	}

}
