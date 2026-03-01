package ru.iteco.fmhandroid.ui.chert.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage.newsRecyclerViewID;
import static ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage.newsStatusId;
import static ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage.postDescrId;
import static ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage.publicationDateId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.chert.data.Data;
import ru.iteco.fmhandroid.ui.chert.data.Helper;
import ru.iteco.fmhandroid.ui.chert.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    String creatingNewPostTitle = Data.creatingRandomTitle();
    String creatingNewPostDescr = Data.creatingPublicationDescription();
    String wrongCategory = "TestingCategory";

    @Before
    public void setUp() {
        try {
            mainPage.checkVisibilityOfMainPage();
        } catch (Exception e) {
            authorizationPage.signInButtonCheckVisibility();
            authorizationPage.successfulAuthorization();
            mainPage.checkVisibilityOfMainPage();
        }
    }

    @DisplayName("Создание новой актуальной новости")
    @Test
    public void creatingNewActualPost() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(0);
        newsPage.setPublicationTime(-1);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);
    }

    @DisplayName("Создание новой новости для будущего")
    @Test
    public void creatingNewFuturePost() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(2);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);
    }

    @DisplayName("Сортировка списка новостей")
    @Test
    public void sortingNewsList() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        String firstPostCreationDateBefSort = newsPage.getCreationDateOfThePost(NewsPage.publicationDateFieldID, 0);
        String lastPostCreationDateBefSort = newsPage.
                getCreationDateOfThePost(NewsPage.publicationDateFieldID, (newsPage.countAmountOfNewsElements() - 1));
        newsPage.clickSortNewsButton();
        String firstPostCreationDateAftSort = newsPage.getCreationDateOfThePost(NewsPage.publicationDateFieldID, 0);
        String lastPostCreationDateAftSort = newsPage.
                getCreationDateOfThePost(NewsPage.publicationDateFieldID, (newsPage.countAmountOfNewsElements() - 1));
        assertEquals(firstPostCreationDateBefSort, lastPostCreationDateAftSort);
        assertEquals(lastPostCreationDateBefSort, firstPostCreationDateAftSort);
    }

    /// /////////////////////////////////////////////////////////////////////
    @DisplayName("Фильтрация списка новостей по категории")
    @Test
    public void filteringNewsListWithCategory() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        String randomCategory = Helper.randomCategory();
        newsPage.setNewsCategory(randomCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(2);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        int itemsBeforeFiltering = Helper.getRecyclerViewItemCount(newsRecyclerViewID);
        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.setPostRandomCategoryWithPopupList(randomCategory);


        newsPage.clickFilterButton();
        newsPage.checkVisibilityOfControlPanel();
        int itemsAfterFiltering = Helper.getRecyclerViewItemCount(newsRecyclerViewID);
        assertNotEquals(itemsBeforeFiltering, itemsAfterFiltering);

    }

    /// /////////////////////////////////////////////////////////////////////////

    @DisplayName("Фильтрация списка новостей по категории")
    @Test
    public void filteringNewsListWithWrongCategory() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();

        newsPage.setNewsCategory(creatingNewPostTitle);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(2);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkCategoryErrorMessage();

    }

    @DisplayName("Фильтрация списка новостей по дате публикации")
    @Test
    public void filteringNewsListWithDate() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();

        newsPage.setPublicationDateStartFilter(newsPage.setPublicationDateStart(1));
        newsPage.setPublicationDateEndFilter(newsPage.setPublicationDateEnd(1));
        newsPage.clickFilterButton();
        newsPage.checkAllNewsNeededField(newsPage.setPublicationDateStart(1), newsRecyclerViewID, publicationDateId);
    }

    @DisplayName("Попытка фильтрации списка новостей при заполнении только начала интервала дат")
    @Test
    public void filteringNewsListWithOnlyStartDate() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.setPublicationDateStartFilter(newsPage.setPublicationDateStart(1));
        newsPage.clickFilterButton();
        newsPage.filterWithWrongDateErrorMesDisplay();
    }

    @DisplayName("Попытка фильтрации списка новостей при заполнении только начала интервала дат")
    @Test
    public void filteringNewsListWithOnlyEndDate() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.setPublicationDateEndFilter(newsPage.setPublicationDateEnd(1));
        newsPage.clickFilterButton();
        newsPage.filterWithWrongDateErrorMesDisplay();
    }

    @DisplayName("Фильтрация активных новостей")
    @Test
    public void filteringActiveNewsList() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.changeSwitcherPosition();
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.clickNotActiveCheckboxFilter();
        newsPage.clickFilterButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.checkAllNewsNeededField("Active", newsRecyclerViewID, newsStatusId);

    }

    @DisplayName("Фильтрация неактивных новостей")
    @Test
    public void filteringNonActiveNewsList() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.changeSwitcherPosition();
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.clickActiveCheckboxFilter();
        newsPage.clickFilterButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.checkAllNewsNeededField("Not active", newsRecyclerViewID, newsStatusId);

    }

    @DisplayName("Фильтрация активных новостей по дате публикации и активному статусу")
    @Test
    public void filteringNewsListByPublicationDateAndStatus() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.changeSwitcherPosition();
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();

        newsPage.clickFilterNewsButton();
        newsPage.checkVisibilityOfFilterNewsPanel();
        newsPage.setPublicationDateStartFilter(newsPage.setPublicationDateStart(1));
        newsPage.setPublicationDateEndFilter(newsPage.setPublicationDateEnd(1));
        newsPage.clickActiveCheckboxFilter();
        newsPage.clickFilterButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.checkAllNewsNeededField("Not active", newsRecyclerViewID, newsStatusId);
        newsPage.checkAllNewsNeededField(newsPage.setPublicationDateStart(1), newsRecyclerViewID, publicationDateId);

    }

    @DisplayName("Редактирование категории новости")
    @Test
    public void editingNewsCategory() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsCategory(Data.profunoinCategory);

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);
        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.checkNewPostCategory(Data.profunoinCategory);
    }

    @DisplayName("Редактирование заголовка новости")
    @Test
    public void editingNewsTitle() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsTitle(creatingNewPostTitle + "NEW");

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle + "NEW");
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle + "NEW");
    }

    @DisplayName("Редактирование даты публикации новости")
    @Test
    public void editingNewsPublicationDate() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        String editDate = Data.creatingPublicationDate(7);
        newsPage.setPublicationDate(7);

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);
    }

    @DisplayName("Редактирование новости описания")
    @Test
    public void editingNewsPublicationDescription() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);


        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsDescription(creatingNewPostDescr + "NEW");


        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        int counterPosition =
                newsPage.checkPositionWithDescription(creatingNewPostDescr + "NEW", newsRecyclerViewID, postDescrId);

        newsPage.checkPostDescription(postDescrId, counterPosition, creatingNewPostDescr + "NEW");

    }

    @DisplayName("Редактирование статуса новости")
    @Test
    public void editingNewsPublicationStatus() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.changeSwitcherPosition();

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        int counterPosition =
                newsPage.checkPositionWithDescription(creatingNewPostDescr, newsRecyclerViewID, postDescrId);
        newsPage.checkPostStatus(newsStatusId, counterPosition, "Not active");

    }

    @DisplayName("Редактирование всех полей уже созданной новости")
    @Test
    public void editingAllNewsFields() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsTitle(creatingNewPostTitle + "NEW");
        String editDate = Data.creatingPublicationDate(7);
        newsPage.setPublicationDate(7);
        newsPage.setNewsDescription(creatingNewPostDescr + "NEW");
        newsPage.changeSwitcherPosition();

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle + "NEW");
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle + "NEW");
//        newsPage.checkSearchResultIsDisplayed(editDate);
        int counterPosition =
                newsPage.checkPositionWithDescription(creatingNewPostDescr + "NEW", newsRecyclerViewID, postDescrId);

        newsPage.checkPostDescription(postDescrId, counterPosition, creatingNewPostDescr + "NEW");
        newsPage.checkPostStatus(newsStatusId, counterPosition, "Not active");

    }

    @DisplayName("Отмена изменений всех полей в новости")
    @Test
    public void cancellingEditionOfAllNewsFields() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        String currentDate = Data.creatingPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsTitle(creatingNewPostTitle + "NEW");
        String editDate = Data.creatingPublicationDate(7);
        newsPage.setPublicationDate(7);
        newsPage.setNewsDescription(creatingNewPostDescr + "NEW");
        newsPage.changeSwitcherPosition();

        newsPage.clickCancelNewPostButton();
        newsPage.clickOkInAlertDialog();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(currentDate);
        int counterPosition =
                newsPage.checkPositionWithDescription(creatingNewPostDescr, newsRecyclerViewID, postDescrId);

        newsPage.checkPostDescription(postDescrId, counterPosition, creatingNewPostDescr);
        newsPage.checkPostStatus(newsStatusId, counterPosition, "Active");

    }

    @DisplayName("Отмена отмены изменений всех полей в новости")
    @Test
    public void cancelCancellingEditionOfAllNewsFields() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickEditButton(creatingNewPostTitle);
        newsPage.checkVisibilityOfEditingNewsWindow();
        newsPage.setNewsTitle(creatingNewPostTitle + "NEW");
        newsPage.setPublicationDate(7);
        String editDate = Data.creatingPublicationDate(7);
        newsPage.setNewsDescription(creatingNewPostDescr + "NEW");
        newsPage.changeSwitcherPosition();

        newsPage.clickCancelNewPostButton();
        newsPage.clickCancelInAlertDialog();
        newsPage.checkVisibilityOfEditingNewsWindow();

        newsPage.checkTitleInEditingMode(creatingNewPostTitle + "NEW");
        newsPage.checkDateInEditingMode(editDate);
        newsPage.checkDescriptionInEditingMode(creatingNewPostDescr + "NEW");
        newsPage.checkSwitcherInEditingMode("Not active");

    }

    @DisplayName("Удаление созданной новости")
    @Test
    public void deletingNewPost() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(Data.thanksgivingCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.searchingNewPostTitle(creatingNewPostTitle);
        newsPage.checkSearchResultIsDisplayed(creatingNewPostTitle);

        newsPage.clickDeleteButton(creatingNewPostTitle);
        newsPage.clickOkInAlertDialog();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickSortNewsButton();
        newsPage.checkPostDoesNotExist(creatingNewPostTitle);

    }

    @DisplayName("Создание новой новости со всеми пустыми полями")
    @Test
    public void tryToCreatePostWithEmptyFields() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();

        newsPage.clickSaveNewPostButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.checkErrorMessage();


    }

    @DisplayName("Создание новой новости с категорией, отстутствующей в выпадающем списке")
    @Test
    public void tryToCreatePostWithNoListCategory() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
        newsPage.clickEditNewsButton();
        newsPage.checkVisibilityOfControlPanel();
        newsPage.clickAddNewsButton();
        newsPage.checkVisibilityOfCreatingNewsWindow();
        newsPage.setNewsCategory(wrongCategory);
        newsPage.setNewsTitle(creatingNewPostTitle);
        newsPage.setPublicationDate(1);
        newsPage.setPublicationTime(0);
        newsPage.setNewsDescription(creatingNewPostDescr);
        newsPage.clickSaveNewPostButton();
        newsPage.checkCategoryErrorMessage();
    }

}
