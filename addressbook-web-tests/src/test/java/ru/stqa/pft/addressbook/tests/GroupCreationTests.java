package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    // ищем в новом списке групп, группу с максимальным идентификатором ID

    // способ №1
//    int max = 0;
//    for (GroupData g : after){
//      if(g.getId() > max){
//        max = g.getId();
//      }
//    }

    // способ №2
//    Comparator<? super GroupData> ById = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(),o2.getId());
//      }
//    };
//    int max = after.stream().max(ById).get().getId();

    // способ №3 заменяем компоратор с переопределённым методом на лямда выражение (анонимную функцию) и вставляем в метод маx()
    // - список превращаем в поток
    // - по этому потоку пробегается функция сравниватель, находит максимальный элемент
//  int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();

    // записываем найденый идентификатор к добавленной группе (в момент создания мы его не знали)
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    //добавляем обновлённую группу к изначальному списку для сравнения
    before.add(group);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
