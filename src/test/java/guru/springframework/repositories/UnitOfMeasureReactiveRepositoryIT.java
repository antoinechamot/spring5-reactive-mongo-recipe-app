package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

	private static final String DESCRIPTION = "UOM";
	@Autowired
	private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

	@Before
	public void setUp() throws Exception {
		unitOfMeasureReactiveRepository.deleteAll().block();
	}
	
	
	@Test
	public void testSave() throws Exception{
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(DESCRIPTION);
		unitOfMeasureReactiveRepository.save(uom).block();
		Long count = unitOfMeasureReactiveRepository.count().block();
		assertEquals(Long.valueOf(1L), count);
	}
	
	
	@Test
	 public void findByDescription() throws Exception {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(DESCRIPTION);
		unitOfMeasureReactiveRepository.save(uom).block();
		UnitOfMeasure fetchedUom = unitOfMeasureReactiveRepository.findByDescription(DESCRIPTION).block();
		assertEquals(DESCRIPTION, fetchedUom.getDescription());
	 }

}
