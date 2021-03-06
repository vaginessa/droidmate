// DroidMate, an automated execution generator for Android apps.
// Copyright (C) 2012-2016 Konrad Jamrozik
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// email: jamrozik@st.cs.uni-saarland.de
// web: www.droidmate.org

package org.droidmate.apk_inliner

import com.konradjamrozik.ResourcePath
import groovy.transform.TypeChecked
import org.droidmate.misc.BuildConstants
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import static groovy.transform.TypeCheckingMode.SKIP

@TypeChecked(SKIP)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4)
public class ApkInlinerFrontendTest
{

  /**
   * <p>
   * The test check if apk inliner successfully inlines an apk without throwing an exception. It doesn't check if the inlined
   * functionality works as expected. For that, please refer to tests using {@code org.droidmate.test.MonitoredInlinedApkFixture}.
   *
   * </p>
   */
  @Test
  public void "Inlines apk"()
  {
    Path inputApkFixturesDir = new ResourcePath(BuildConstants.apk_fixtures).path
    assert Files.isDirectory(inputApkFixturesDir)
    assert Files.list(inputApkFixturesDir).count() == 1
    
    Path inputApkFixture = new ApkPath(Files.list(inputApkFixturesDir).find() as Path).path
    assert inputApkFixture.fileName.toString() == "com.estrongs.android.taskmanager.apk"

    Path inputDir = Paths.get("tmp-test-toremove_input-apks")
    Path outputDir = Paths.get("tmp-test-toremove_output-apks")
    inputDir.deleteDir()
    outputDir.deleteDir()
    Files.createDirectory(inputDir)
    Files.createDirectory(outputDir)

    Files.copy(inputApkFixture, inputDir.resolve(inputApkFixture.getFileName()))

    ApkInlinerFrontend.handleException = {Exception e -> throw e}
    // Act
    ApkInlinerFrontend.main([
      BuildConstants.apk_inliner_param_input, inputDir.toAbsolutePath().toString(),
      BuildConstants.apk_inliner_param_output_dir, outputDir.toAbsolutePath().toString()
    ] as String[])

    assert Files.list(outputDir).count() == 1
    assert new ApkPath(Files.list(outputDir).find() as Path).path

  }

}