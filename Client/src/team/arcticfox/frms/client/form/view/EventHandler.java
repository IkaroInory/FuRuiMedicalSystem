package team.arcticfox.frms.client.form.view;

import team.arcticfox.frms.client.environment.Environment;
import team.arcticfox.frms.database.Database;
import team.arcticfox.frms.data.MedicineInfo;
import team.arcticfox.frms.system.Function;
import team.arcticfox.frms.system.SystemEnvironment;

import javax.swing.*;
import java.awt.*;

final class EventHandler {
    private static void loadLang(View view) {
        view.setTitle(Environment.language.form.view.formTitle);
        view.labelIdLabel.setText(Environment.language.form.view.labelId);
        view.labelApprovalNoLabel.setText(Environment.language.form.view.labelApprovalNo);
        view.labelTypeLabel.setText(Environment.language.form.view.labelType);
        view.labelSpecificationLabel.setText(Environment.language.form.view.labelSpecification);
        view.labelManufacturerLabel.setText(Environment.language.form.view.labelManufacturer);
        view.buttonAddToCart.setText(Environment.language.form.view.buttonAddToCart);
    }

    static MedicineInfo initialize(View view, int id) {
        loadLang(view);
        Database db = new Database(Environment.config.database.address.ip, Environment.config.database.address.port, SystemEnvironment.DB_NAME);
        db.open(Environment.config.database.root.username, Environment.config.database.root.password);
        MedicineInfo info = MedicineInfo.parse(db.sqlQuery(Function.getSQL_Query_MedicineList_ById(id))).get(0);
        db.close();

        ImageIcon medicineImage = new ImageIcon(view.getClass().getResource("/images/" + info.imageName));
        medicineImage.setImage(medicineImage.getImage().getScaledInstance(250, 250, Image.SCALE_AREA_AVERAGING));
        view.labelImage.setIcon(medicineImage);

        view.labelGrade.setText(view.labelGrade.getText().replaceAll("%grade%", info.grade.getLabel()));
        view.labelMedicineName.setText(view.labelMedicineName.getText().replaceAll("%medicine_name%", info.medicineName));
        view.labelPrice.setText(view.labelPrice.getText().replaceAll("%price%", String.valueOf(info.price)));
        view.labelId.setText(view.labelId.getText().replaceAll("%id%", String.valueOf(info.id)));
        view.labelApprovalNo.setText(view.labelApprovalNo.getText().replaceAll("%approval_no%", info.approvalNo));
        view.labelType.setText(view.labelType.getText().replaceAll("%type%", info.type.getLabel()));
        view.labelSpecification.setText(view.labelSpecification.getText().replaceAll("%specification%", info.specification));
        view.labelManufacturer.setText(view.labelManufacturer.getText().replaceAll("%manufacturer%", info.manufacturer));

        view.setTitle(view.getTitle().replaceAll("%medicine_name%", info.medicineName));

        ImageIcon copyImage = new ImageIcon(view.getClass().getResource("/icons/copy.png"));
        copyImage.setImage(copyImage.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
        view.buttonCopyMedicineName.setIcon(copyImage);
        view.buttonCopyApprovalNo.setIcon(copyImage);
        view.buttonCopyType.setIcon(copyImage);
        view.buttonCopySpecification.setIcon(copyImage);
        view.buttonCopyManufacturer.setIcon(copyImage);
        return info;
    }
}
