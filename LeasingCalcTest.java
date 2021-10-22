package org.selenide.examples;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class LeasingCalcTest {
    @Test
    public void KuumakseNouetekohaseSoidukiHinnaga() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("price")).setValue("7500").pressEnter();
        $(By.className("payment")).shouldHave(Condition.text("96.37"));

    }
    @Test
    public void KuumakseNegatiivseSoidukiHinnaga(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("price")).setValue("-7500").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }
    @Test
    public void KuumakseMittenouetekohaseSoidukiHinnaga(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("price")).setValue("1").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void KuumakseSoidukiHindSonadega(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("price")).setValue("pole?").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }
    @Test
    public void SissemakseProtsendiMuutmine() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial_percentage")).setValue("20").pressEnter();
        $(By.id("initial")).shouldHave(Condition.value("3000"));
    }

    @Test
    public void SissemakseProtsendiMuutmineMitteNouetekohane() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial_percentage")).setValue("1").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }
    @Test
    public void SissemakseProtsendiMuutmineMitteNumbrid() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial_percentage")).setValue("iii*&###").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void SissemakseProtsendiMuutmineNeg() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial_percentage")).setValue("-10").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void SissemakseSummaMuutmine() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial")).setValue("1899.9").pressEnter();
        $(By.id("initial_percentage")).shouldHave(Condition.value("12.67"));
    }

    @Test
    public void SissemakseSummaMuutmineNeg() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("initial")).setValue("-1000").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void JaakvaartuseProtsendiMuutmine() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("reminder_percentage")).setValue("15").pressEnter();
        $(By.id("reminder")).shouldHave(Condition.value("2250"));
        $(By.xpath("//span[contains(text(),'Maksegraafik')]")).shouldBe(Condition.visible).click();
        $(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[2]/tr[74]/td[2]")).shouldHave(Condition.value("2250"));
        
    }

    @Test
    public void JaakvaartuseProtsendiMuutmineNeg() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("reminder_percentage")).setValue("-10").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void JaakvaartuseProtsendiMuutmineNumbridMittenumbridSegamini() {
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.id("reminder_percentage")).setValue("%1%0").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
    }

    @Test
    public void UksiTaolemiselMakimaalseKuumakseArvutNouetekohaneSissetulek(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.xpath("//a[contains(text(),'Maksimaalne kuumakse')]")).shouldBe(Condition.visible).click();
        $(By.id("monthly-income")).setValue("800").pressEnter();
        $(By.className("payment")).shouldBe(Condition.visible);
    }

    @Test
    public void UksiTaolemiselMakimaalseKuumakseArvutMitteouetekohaneSissetulek(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.xpath("//a[contains(text(),'Maksimaalne kuumakse')]")).shouldBe(Condition.visible).click();
        $(By.id("monthly-income")).setValue("799").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
        $(By.xpath("//div[contains(text(),'Maksimaalse kuumakse arvutamiseks on netosissetule')]")).shouldHave(Condition.text("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike."));
    }

    @Test
    public void UksiTaolemiselMakimaalseKuumakseArvutNegSissetulek(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.xpath("//a[contains(text(),'Maksimaalne kuumakse')]")).shouldBe(Condition.visible).click();
        $(By.id("monthly-income")).setValue("-1500").pressEnter();
        $(By.className("payment")).shouldNotBe(Condition.visible);
        $(By.xpath("//div[contains(text(),'Maksimaalse kuumakse arvutamiseks on netosissetule')]")).shouldHave(Condition.text("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike."));
    }

    @Test
    public void KaastaotlejagaMaksKuumakseNouetekohaneSIssetulek(){
        open("https://www.lhv.ee/et/liising");
        $(By.id("acceptPirukas")).click();
        $(By.xpath("//a[contains(text(),'Maksimaalne kuumakse')]")).shouldBe(Condition.visible).click();
        $(By.className("radio")).selectRadio("guarantor");
        $(By.id("guarantor-monthly-income")).setValue("200").pressEnter();
        $(By.className("payment")).shouldBe(Condition.visible);

    }



}