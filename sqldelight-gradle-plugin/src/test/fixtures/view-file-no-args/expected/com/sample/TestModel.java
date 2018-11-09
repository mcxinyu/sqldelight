package com.sample;

import android.database.Cursor;
import androidx.annotation.NonNull;
import com.squareup.sqldelight.prerelease.RowMapper;
import com.squareup.sqldelight.prerelease.SqlDelightQuery;
import com.squareup.sqldelight.prerelease.internal.TableSet;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;

public interface TestModel {
  @Deprecated
  String TESTVIEW_VIEW_NAME = "testView";

  String CREATE_VIEW = ""
      + "CREATE VIEW testView AS SELECT * FROM settings";

  interface TestViewModel<T1 extends SettingsModel> {
    @NonNull
    T1 settings();
  }

  interface TestViewCreator<T1 extends SettingsModel, T extends TestViewModel<T1>> {
    T create(@NonNull T1 settings);
  }

  final class TestViewMapper<T1 extends SettingsModel, T extends TestViewModel<T1>> implements RowMapper<T> {
    private final TestViewCreator<T1, T> creator;

    private final SettingsModel.Factory<T1> settingsModelFactory;

    public TestViewMapper(TestViewCreator<T1, T> creator,
        @NonNull SettingsModel.Factory<T1> settingsModelFactory) {
      this.creator = creator;
      this.settingsModelFactory = settingsModelFactory;
    }

    @Override
    @NonNull
    public T map(@NonNull Cursor cursor) {
      return creator.create(
          settingsModelFactory.creator.create(
              cursor.getLong(0)
          )
      );
    }
  }

  final class Factory<T1 extends SettingsModel> {
    SettingsModel.Factory<T1> settingsModelFactory;

    public Factory(SettingsModel.Factory<T1> settingsModelFactory) {
      this.settingsModelFactory = settingsModelFactory;
    }

    @NonNull
    public SqlDelightQuery load() {
      return new SqlDelightQuery(""
          + "SELECT * FROM testView",
          new TableSet("settings"));
    }

    @NonNull
    public SqlDelightQuery load2() {
      return new SqlDelightQuery(""
          + "SELECT * FROM testView WHERE row_id = 1",
          new TableSet("settings"));
    }

    @NonNull
    public <R extends TestViewModel<T1>> TestViewMapper<T1, R> loadMapper(
        TestViewCreator<T1, R> creator) {
      return new TestViewMapper<T1, R>(creator, settingsModelFactory);
    }

    @NonNull
    public <R extends TestViewModel<T1>> TestViewMapper<T1, R> load2Mapper(
        TestViewCreator<T1, R> creator) {
      return new TestViewMapper<T1, R>(creator, settingsModelFactory);
    }
  }
}
