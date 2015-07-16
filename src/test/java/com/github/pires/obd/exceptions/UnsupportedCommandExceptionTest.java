/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.exceptions;

import com.github.pires.obd.exceptions.UnsupportedCommandException;
import static org.powermock.api.easymock.PowerMock.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.pires.obd.commands.SpeedObdCommand;

/**
 * Test results with echo on and off.
 */
@PrepareForTest(InputStream.class)
public class UnsupportedCommandExceptionTest {

  private SpeedObdCommand command;
  private InputStream mockIn;

  /**
   * @throws Exception
   */
  @BeforeMethod
  public void setUp() throws Exception {
    command = new SpeedObdCommand();
  }

  /**
   * Test for valid InputStream read with echo
   *
   * @throws java.io.IOException, java.lang.InterruptedException
   */
  @Test(expectedExceptions = UnsupportedCommandException.class)
  public void testUnsupportedVin() throws IOException, InterruptedException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '7');
    expectLastCall().andReturn((byte) 'F');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '9');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) '2');
    expectLastCall().andReturn((byte) '>');

    replayAll();

    // call the method to test
    command.run(mockIn, new ByteArrayOutputStream());

    verifyAll();
  }
   /**
   * Test for valid InputStream read with echo
   *
   * @throws java.io.IOException, java.lang.InterruptedException
   */
  @Test(expectedExceptions = UnsupportedCommandException.class)
  public void testUnsupportedSpeed() throws IOException, InterruptedException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '7');
    expectLastCall().andReturn((byte) 'F');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) '2');
    expectLastCall().andReturn((byte) '>');

    replayAll();

    // call the method to test
    command.run(mockIn, new ByteArrayOutputStream());

    verifyAll();
  }

}
