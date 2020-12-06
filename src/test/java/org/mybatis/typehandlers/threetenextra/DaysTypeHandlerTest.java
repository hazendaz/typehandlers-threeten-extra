/**
 *    Copyright 2017-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.typehandlers.threetenextra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.ibatis.type.TypeHandler;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

/**
 * @author Björn Raupach
 */
public class DaysTypeHandlerTest extends BaseTypeHandlerTest {

  private static final TypeHandler<Days> TYPE_HANDLER = new DaysTypeHandler();

  private static final Days DAYS = Days.ZERO;

  @Override
  @Test
  public void shouldSetParameter() throws Exception {
    TYPE_HANDLER.setParameter(ps, 1, DAYS, null);
    verify(ps).setInt(1, DAYS.getAmount());
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByName() throws Exception {
    when(rs.getInt("column")).thenReturn(DAYS.getAmount());
    assertThat(TYPE_HANDLER.getResult(rs, "column")).isEqualTo(DAYS);
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByName() throws Exception {
    when(rs.getInt("column")).thenReturn(0);
    when(rs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(rs, "column")).isNull();
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByPosition() throws Exception {
    when(rs.getInt(1)).thenReturn(DAYS.getAmount());
    assertThat(TYPE_HANDLER.getResult(rs, 1)).isEqualTo(DAYS);
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByPosition() throws Exception {
    when(rs.getInt(1)).thenReturn(0);
    when(rs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(rs, 1)).isNull();
  }

  @Override
  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getInt(1)).thenReturn(DAYS.getAmount());
    assertThat(TYPE_HANDLER.getResult(cs, 1)).isEqualTo(DAYS);
  }

  @Override
  @Test
  public void shouldGetResultNullFromCallableStatement() throws Exception {
    when(cs.getInt(1)).thenReturn(0);
    when(cs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(cs, 1)).isNull();
  }

}
